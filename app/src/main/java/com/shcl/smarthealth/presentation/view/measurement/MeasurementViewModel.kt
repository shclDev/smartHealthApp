package com.shcl.smarthealth.presentation.view.measurement

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.omron.BloodPressure
import com.shcl.smarthealth.domain.model.omron.BodyComposition
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyDiseaseType
import com.shcl.smarthealth.domain.usecase.isens.IsensDeviceUseCase
import com.shcl.smarthealth.domain.usecase.isens.IsensScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import com.shcl.smarthealth.presentation.view.device.MeasurementStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQMeasurementRecordKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

enum class MeasurementStep(val num : Int , val title : String, val displayTime:Int){

    HELLO(1,"안녕하세요,사용자님!" , 7 ),
    WATCH_LOADING(2,"새로운 건강 기록을 불러오는 중입니다.",7 ),
    WATCH_LOAD_SUCCESS(3,"새로운 건강 기록을 불러오는데 성공했습니다.",5),
    BLOOD_PRESSURE_INIT(4,"혈압을 측정하겠습니다.\n연동된 기기를 꺼내어 혈압을 측정해주세요.",50),
    BLOOD_PRESSURE_WAIT(5,"측정값을 불러오는 중입니다." , 100),
    BLOOD_PRESSURE_FAIL(6,"재측정이 필요합니다.\n[다시 측정하기] 버튼을 눌러주세요." , 100),
    BLOOD_PRESSURE_SUCCESS(7,"",100),

    BLOOD_SUGAR_INIT(8,"혈당을 측정하겠습니다.\n연동된 기기를 꺼내어 혈당을 측정해주세요.",20),
    BLOOD_SUGAR_WAIT(9,"측정값을 불러오는 중입니다." , 100),
    BLOOD_SUGAR_SUCCESS(10,"",100),

    WEIGHT_INIT(11,"몸무게를 측정하겠습니다.\n연동된 기기를 꺼내어 몸무게를 측정해주세요.",20),
    WEIGHT_WAIT(12,"측정값을 불러오는 중입니다." , 100),
    WEIGHT_SUCCESS(13,"",100),

    ALL_COMPLTE(14,"잠시만 기다려주세요.\n곧 귀하의 건강 기록을 분석하여 보고드리겠습니다.", 10),
    ERR_PAGE(15,"",100)
}



@HiltViewModel
class MeasurementViewModel @Inject constructor(
    private val voiceUseCase: VoiceUseCase,
    private val userUseCase: UserUseCase,
    private val omronDeviceUseCase: OmronDeviceUseCase,
    private val isensDeviceUseCase : IsensDeviceUseCase
) : ViewModel() {

    private var _measurementStep = MutableStateFlow<MeasurementStep>(MeasurementStep.HELLO)
    var measurementStep = _measurementStep.asStateFlow()

    private val _loggedUserState = MutableStateFlow<LastedLoginUserRoom?>(null)
    val loggedUserState = _loggedUserState.asStateFlow()

    private val _deviceState = MutableStateFlow<DiscoveredDevice?>(null)
    val deviceState = _loggedUserState.asStateFlow()

    private val _titleText = MutableStateFlow<String>("")
    val titleText = _titleText.asStateFlow()

    private val _measurementText = MutableStateFlow<String>("")
    val measurementText = _measurementText.asStateFlow()

    private val _measurementState = MutableStateFlow<MeasurementStatus>(MeasurementStatus.Unknown)
    val measurementState = _measurementState.asStateFlow()

    private val _iSensMeasurementState = MutableStateFlow(IsensGlucoseRecordState(status = MeasurementStatus.Unknown, records = null))
    val isensMeasurementState = _iSensMeasurementState.asStateFlow()


    var stepNum = 1;

    private lateinit var omronJob : Job
    private lateinit var isensJob : Job

    init {
        getISensData()
    }

    fun getDevice(category : String){

        when(category){
            "BloodPressureMonitor","WeightScale"->{
                viewModelScope.launch{
                    omronDeviceUseCase.getDeviceUseCase.getDevice(category)
                        .onStart {  }
                        .onCompletion {  Log.d("smarthealth" , "getDevice onCompletion") }
                        .catch {   Log.d("smarthealth" , "getDevice catch")}
                        .collect{
                            it?.let {

                                Log.d("smarthealth" , it.address!!)
                                Log.d("smarthealth" , it.deviceCategory!!)

                                var category : OHQDeviceCategory = OHQDeviceCategory.Unknown

                                if (it.deviceCategory.contains("BloodPressureMonitor"))
                                    category= OHQDeviceCategory.BloodPressureMonitor
                                else if(it.deviceCategory.contains("WeightScale")){
                                    category =OHQDeviceCategory.BodyCompositionMonitor
                                }else{
                                    category = OHQDeviceCategory.Unknown
                                }

                                _deviceState.value = DiscoveredDevice(
                                    address = it.address!!,
                                    rssi = it.rssi!!,
                                    modelName = it.modelName,
                                    localName = it.localName,
                                    deviceCategory = category
                                )
                            }
                        }
                }
            }
            "Glucose"->{

                viewModelScope.launch{
                    isensDeviceUseCase.getIsensDeviceUseCase.invoke(category)
                        .onStart {  }
                        .onCompletion {  Log.d("smarthealth" , "getDevice onCompletion") }
                        .catch {   Log.d("smarthealth" , "getDevice catch")}
                        .collect{
                            it?.let {
                                Log.d("smarthealth" , "Glucose ${it.address!!}")
                                Log.d("smarthealth" , "Glucose ${it.deviceCategory!!}")

                                var category : OHQDeviceCategory = OHQDeviceCategory.Unknown

                                if(it.deviceCategory.contains("Glucose")){
                                    category = OHQDeviceCategory.Glucose
                                }else{
                                    category = OHQDeviceCategory.Unknown
                                }

                                _deviceState.value = DiscoveredDevice(
                                    address = it.address,
                                    rssi = it.rssi!!,
                                    modelName = it.modelName,
                                    localName = it.localName,
                                    deviceCategory = category
                                )
                            }

                        }

                }

            }
        }

    }

    fun periodicOmronExecution( timeSec : Int ){

        var timerSec = timeSec

        omronJob = viewModelScope.launch {
            while(timerSec > 0){
                timerSec = timerSec.minus(1)
                delay(1000L)

                if(timerSec % 3 == 0){
                    getOmronMeasurementRecord()
                }
            }
        }
    }

    fun periodicISensExecution( timeSec : Int){

        var timerSec = timeSec

        isensJob = viewModelScope.launch {
            while(timerSec > 0){
                timerSec = timerSec.minus(1)
                delay(1000L)

                if(timerSec % 3 == 0){
                    getISensMeasurementRecord()
                }
            }
        }
    }

    fun isensTimerStop(){
        if(::isensJob.isInitialized) isensJob.cancel()
    }


    fun omronTimerStop(){
        if(::omronJob.isInitialized) omronJob.cancel()
        //stepJump(MeasurementStep.ERR_PAGE)
    }


    fun getISensMeasurementRecord(){
        viewModelScope.launch {
            isensDeviceUseCase.isensScanDeviceUseCase.connect("74:46:B3:4E:30:F7")
            isensDeviceUseCase.getGlucoseRecordUseCase.requestAllRecords()
        }
    }

    fun getISensData(){
        viewModelScope.launch {
            isensDeviceUseCase.getGlucoseRecordUseCase.getDataTransfer().collect{ it->
                if(it.status == MeasurementStatus.Success){
                    _iSensMeasurementState.value = it

                    it.records?.let {
                        isensTimerStop()
                        val lastRecord = it[it.size()]

                        lastRecord?.let {
                            Log.d("smarthealth" , lastRecord.toString())
                            _measurementState.value = MeasurementStatus.Success
                            _measurementText.value = "${lastRecord.glucoseData}"
                            _titleText.value = "측정된 혈당은 ${lastRecord.glucoseData} 입니다"
                            stepJump(MeasurementStep.BLOOD_SUGAR_SUCCESS)
                        }
                    }
                }else{
                    it.records?.let {
                        isensTimerStop()
                        val lastRecord = it[it.size()]

                        lastRecord?.let {
                            Log.d("smarthealth" , lastRecord.toString())
                            _measurementState.value = MeasurementStatus.Success
                            _measurementText.value = "${lastRecord.glucoseData}"
                            _titleText.value = "측정된 혈당은 ${lastRecord.glucoseData} 입니다"
                            stepJump(MeasurementStep.BLOOD_SUGAR_SUCCESS)
                        }
                    }


                }
            }
        }
    }



    fun getOmronMeasurementRecord() {

        viewModelScope.launch{
            omronDeviceUseCase.getDataTransferUseCase.getDataTransfer(_deviceState.value ,RequestType.DataTransfer).collect{

                if((it.status == MeasurementStatus.Success)){
                    try{
                        val measurementRecord : Map<OHQMeasurementRecordKey , Any>? = it.sessionData?.measurementRecord?.get(0)

                        when(it.category){
                            OHQDeviceCategory.BloodPressureMonitor->{

                                measurementRecord?.let {
                                    val bloodPressure = BloodPressure(
                                        diastolic = measurementRecord.get(OHQMeasurementRecordKey.DiastolicKey).toString().toFloat(),
                                        diastolicUnit = measurementRecord.get(OHQMeasurementRecordKey.BloodPressureUnitKey).toString(),
                                        systolic = measurementRecord.get(OHQMeasurementRecordKey.SystolicKey).toString().toFloat(),
                                        systolicUnit = measurementRecord.get(OHQMeasurementRecordKey.BloodPressureUnitKey).toString(),
                                        pulseRate = measurementRecord.get(OHQMeasurementRecordKey.PulseRateKey).toString().toFloat(),
                                        timeStamp = measurementRecord.get(OHQMeasurementRecordKey.TimeStampKey).toString()
                                    )
                                    omronTimerStop()
                                    _measurementState.value = MeasurementStatus.Success
                                    _measurementText.value = "${bloodPressure.systolic} / ${bloodPressure.diastolic} ${bloodPressure.systolicUnit}"
                                    _titleText.value = "측정된 혈압은 ${bloodPressure.systolic} / ${bloodPressure.diastolic} ${bloodPressure.systolicUnit} 입니다."
                                    stepJump(MeasurementStep.BLOOD_PRESSURE_SUCCESS)
                                }
                            }

                            OHQDeviceCategory.BodyCompositionMonitor->{

                                _measurementText.value = ""
                                _titleText.value = ""

                                    measurementRecord?.let{
                                        val userIndexKey = measurementRecord.get(OHQMeasurementRecordKey.UserIndexKey).toString().toInt()
                                        val sequenceNumber = measurementRecord.get(OHQMeasurementRecordKey.SequenceNumberKey).toString().toInt()
                                        val weight = measurementRecord.get(OHQMeasurementRecordKey.WeightKey).toString().toFloat()
                                        val weightUnit = measurementRecord.get(OHQMeasurementRecordKey.WeightUnitKey).toString()
                                        val bodyAge = measurementRecord.get(OHQMeasurementRecordKey.BodyAgeKey).toString().toInt()
                                        val bmi = measurementRecord.get(OHQMeasurementRecordKey.BMIKey).toString().toFloat()
                                        //val musclePercentage = measurementRecord.get(OHQMeasurementRecordKey.MusclePercentageKey).toString().toFloat()
                                        val bodyFatPercentage = measurementRecord.get(OHQMeasurementRecordKey.BodyFatPercentageKey).toString().toFloat()
                                        val skeletalMusclePercentage = measurementRecord.get(OHQMeasurementRecordKey.SkeletalMusclePercentageKey).toString().toFloat()
                                        val timeStamp = measurementRecord.get(OHQMeasurementRecordKey.TimeStampKey).toString()

                                        val bodyComposition = BodyComposition(
                                            userIndex = userIndexKey,
                                            sequenceNumber = sequenceNumber,
                                            weight = weight,
                                            weightUnit = weightUnit,
                                            bodyAge = bodyAge,
                                            bmi = bmi,
                                            musclePercentage =0.0f,
                                            bodyFatPercentage = bodyFatPercentage,
                                            skeletalMusclePercentage = skeletalMusclePercentage,
                                            timeStamp = timeStamp
                                        )
                                        omronTimerStop()
                                        _measurementState.value = MeasurementStatus.Success
                                        _titleText.value = "측정된 몸무게는 ${bodyComposition.weight} 입니다."
                                        _measurementText.value = "${bodyComposition.weight} "
                                        stepJump(MeasurementStep.WEIGHT_SUCCESS)
                                    }

                            }else->{

                            }
                        }

                    }catch (e : Exception){

                        //_measurementState.value = MeasurementStatus.Fail
                        //_titleText.value = "알수없는 오류가 발생했습니다."
                        Log.e("smarthealth" , e.message.toString())
                    }
                }
            }
        }
    }

    fun nextStep(){
        stepNum += 1
        val step = enumValues<MeasurementStep>().find { it.num == stepNum }

        step?.let {
            _measurementStep.value = step
        }
    }

    fun stepJump(jumpStep : MeasurementStep){
        val findStep = enumValues<MeasurementStep>().find { it == jumpStep }

        findStep?.let {
            stepNum = it.num
            _measurementStep.value = it
        }
    }

    fun getLastedLoginUser(){

        viewModelScope.launch{
            userUseCase.lastedLoginUserUseCase.invoke()
                .onStart {   Log.d("smarthealth" , "loggedUserChk") }
                .onCompletion {  Log.d("smarthealth" , "loggedUserChk onCompletion") }
                .catch {   Log.d("smarthealth" , "loggedUserChk catch")}
                .collect{
                    it.let {
                        Log.d("smarthealth" , "loggedUser : $it")
                        _loggedUserState.value = it
                    }
                }
        }
    }


    fun clovaVoice(text : String){

        viewModelScope.launch {
            voiceUseCase.voiceTTSUseCase.invoke(
                spearker = "nara",
                text = text)
                .onStart {   Log.d("smarthealth" , "voice start") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it?.let {
                        voiceUseCase.voicePlayUseCase.invoke(it)
                    }
                }
        }
    }


}
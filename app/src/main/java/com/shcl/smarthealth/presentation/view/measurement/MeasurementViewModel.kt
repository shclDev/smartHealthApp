package com.shcl.smarthealth.presentation.view.measurement

import android.health.connect.datatypes.BloodGlucoseRecord
import android.health.connect.datatypes.units.BloodGlucose
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.omron.BloodPressure
import com.shcl.smarthealth.domain.model.omron.BodyComposition
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyDiseaseType
import com.shcl.smarthealth.domain.usecase.isens.IsensDeviceUseCase
import com.shcl.smarthealth.domain.usecase.isens.IsensScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.measurement.MeasurementUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import com.shcl.smarthealth.domain.utils.PreferencesManager
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

    BLOOD_SUGAR_INIT(8,"혈당을 측정하겠습니다.\n연동된 기기를 꺼내어 혈당을 측정해주세요.",15),
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
    private val isensDeviceUseCase : IsensDeviceUseCase,
    private val measurementUseCase: MeasurementUseCase
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

                                var category : OHQDeviceCategory

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

    fun updateBloodPressure(data : BloodPressureRoom){
        viewModelScope.launch {
            measurementUseCase.updateBloodPressureUseCase.invoke(BloodPressureRequest(
                diastolic = data.diastolic,
                diastolicUnit = data.diastolicUnit.uppercase(),
                systolic = data.systolic,
                systolicUnit = data.systolicUnit.uppercase(),
                pulseRate = data.pulseRate,
                timeStamp = data.timeStamp
            ))
            ?.collect{
                    it?.let {

                    }
            }
            omronDeviceUseCase.setBloodPressureUseCase.updateBloodPressureToDB(data)
        }
    }

    fun updateBloodGlucose(data : GlucoseRecordRoom){
        viewModelScope.launch {
            measurementUseCase.updateBloodGlucoseUseCase.invoke(BloodGlucoseRequest(
                glucoseData = data.glucoseData,
                flagCs =  data.flagCs,
                flagHilow = data.flagHilow,
                flagContext = data.flagContext,
                flagMeal = data.flagMeal,
                flagFasting = data.flagFasting,
                flagKetone = data.flagKetone,
                flagNomark = data.flagNomark,
                timeoffset = data.timeOffset,
                time = data.time
            ))
                ?.collect{
                    it?.let {

                    }
            }
            isensDeviceUseCase.setGlucoseRecordUserCase.updateGlucoseRecordToDB(data)
        }
    }

    fun updateBodyCompostion(data : BodyCompositionRoom){
        viewModelScope.launch {
            measurementUseCase.updateBodyCompostionUseCase.invoke(BodyCompositionRequest(
                    userIndex = data.userIndex,
                    sequenceNumber = data.sequenceNumber,
                    weight = data.weight,
                    weightUnit = data.weightUnit.uppercase(),
                    bodyAge = data.bodyAge,
                    bmi = data.bmi,
                    musclePercentage = data.musclePercentage,
                    bodyFatPercentage = data.bodyFatPercentage,
                    skeletalMusclePercentage = data.skeletalMusclePercentage,
                    timeStamp = data.timeStamp
                ))?.collect{
                    it?.let {

                    }
                }

            omronDeviceUseCase.bodyCompositionUseCase.updateBodyCompositionToDB(data)
        }
    }

    fun updateHeight(request : HeightRequest){
        viewModelScope.launch {
            measurementUseCase.updateHeightUseCase.invoke(request)
                ?.collect{
                    it?.let {

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

            _deviceState.value?.let {
                isensDeviceUseCase.isensScanDeviceUseCase.connect(it.address)
                isensDeviceUseCase.getGlucoseRecordUseCase.requestAllRecords()
            }
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

                            val userId = PreferencesManager.getUserId("userId" , 0)

                            updateBloodGlucose(
                                GlucoseRecordRoom(
                                    userId = userId,
                                    glucoseData = lastRecord.glucoseData,
                                    flagCs = lastRecord.flag_cs,
                                    flagHilow = lastRecord.flag_hilow,
                                    flagContext = lastRecord.flag_context,
                                    flagMeal = lastRecord.flag_meal,
                                    flagFasting = lastRecord.flag_fasting,
                                    flagKetone = lastRecord.flag_ketone,
                                    timeOffset = lastRecord.timeoffset,
                                    time = lastRecord.time
                                )
                            )

                            _measurementState.value = MeasurementStatus.Success
                            _measurementText.value = "${lastRecord.glucoseData.toInt()}"
                            _titleText.value = "측정된 혈당은 ${lastRecord.glucoseData.toInt()} mg/dL 입니다"
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

                                    val userId = PreferencesManager.getUserId("userId" , 0)

                                    updateBloodPressure(
                                        BloodPressureRoom(
                                            diastolic = bloodPressure.diastolic,
                                            diastolicUnit = bloodPressure.diastolicUnit,
                                            systolic = bloodPressure.systolic,
                                            systolicUnit = bloodPressure.systolicUnit,
                                            pulseRate = bloodPressure.pulseRate,
                                            timeStamp = bloodPressure.timeStamp,
                                            userId = userId
                                        )
                                    )

                                    omronTimerStop()
                                    _measurementState.value = MeasurementStatus.Success
                                    _measurementText.value = "${bloodPressure.systolic.toInt()} / ${bloodPressure.diastolic.toInt()}"
                                    _titleText.value = "측정된 혈압은 ${bloodPressure.systolic.toInt()} / ${bloodPressure.diastolic.toInt()} ${bloodPressure.systolicUnit} 입니다."
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

                                        val userId = PreferencesManager.getUserId("userId" , 0)

                                        updateBodyCompostion(BodyCompositionRoom(
                                            userId = userId,
                                            userIndex = bodyComposition.userIndex,
                                            sequenceNumber = bodyComposition.sequenceNumber,
                                            weight = bodyComposition.weight,
                                            weightUnit = bodyComposition.weightUnit,
                                            fatPercentage = bodyComposition.bodyFatPercentage,
                                            bodyAge = bodyComposition.bodyAge,
                                            bmi = bodyComposition.bmi,
                                            musclePercentage = bodyComposition.musclePercentage,
                                            bodyFatPercentage = bodyComposition.bodyFatPercentage,
                                            skeletalMusclePercentage = bodyComposition.skeletalMusclePercentage,
                                            timeStamp = bodyComposition.timeStamp
                                        ))

                                        omronTimerStop()
                                        _measurementState.value = MeasurementStatus.Success
                                        _titleText.value = "측정된 몸무게는 ${bodyComposition.weight} Kg 입니다."
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
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
import com.shcl.smarthealth.domain.usecase.isens.IsensScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import com.shcl.smarthealth.presentation.view.device.MeasurementStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQMeasurementRecordKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
    BLOOD_PRESSURE_INIT(4,"혈압을 측정하겠습니다.\n연동된 기기를 꺼내어 혈압을 측정해주세요.",20),
    BLOOD_PRESSURE_WAIT(5,"측정값을 불러오는 중입니다." , 100),
    BLOOD_PRESSURE_FAIL(6,"재측정이 필요합니다.[다시 측정하기] 버튼을 눌러주세요." , 100),
    BLOOD_PRESSURE_SUCCESS(7,"",100),

    BLOOD_SUGAR_INIT(8,"혈당을 측정하겠습니다.\n연동된 기기를 꺼내어 혈당을 측정해주세요.",20),
    BLOOD_SUGAR_WAIT(9,"측정값을 불러오는 중입니다." , 100),
    BLOOD_SUGAR_SUCCESS(10,"",100),

    WEIGHT_INIT(11,"몸무게를 측정하겠습니다.\n연동된 기기를 꺼내어 몸무게를 측정해주세요.",20),
    WEIGHT_WAIT(12,"측정값을 불러오는 중입니다." , 100),
    WEIGHT_SUCCESS(13,"",100),

    ALL_COMPLTE(14,"잠시만 기다려주세요.\n곧 귀하의 건강 기록을 분석하여 보고드리겠습니다.", 10)
}



@HiltViewModel
class MeasurementViewModel @Inject constructor(
    private val voiceUseCase: VoiceUseCase,
    private val userUseCase: UserUseCase,
    private val omronDeviceUseCase: OmronDeviceUseCase,
    private val isensDeviceUseCase : IsensScanDeviceUseCase
) : ViewModel() {

    private var _measurementStep = MutableStateFlow<MeasurementStep>(MeasurementStep.HELLO)
    var measurementStep = _measurementStep.asStateFlow()

    private val _loggedUserState = MutableStateFlow<LastedLoginUserRoom?>(null)
    val loggedUserState = _loggedUserState.asStateFlow()

    private val _deviceState = MutableStateFlow<DiscoveredDevice?>(null)
    val deviceState = _loggedUserState.asStateFlow()

    private val _measurementText = MutableStateFlow<String>("")
    val measurementText = _measurementText.asStateFlow()

    private val _measurementState = MutableStateFlow<MeasurementStatus>(MeasurementStatus.Unknown)
    val measurementState = _measurementState.asStateFlow()

    var stepNum = 1;

    init {


    }

    fun getDevice(category : String){
        viewModelScope.launch{
            omronDeviceUseCase.getDeviceUseCase.getDevice(category)
                .onStart {  }
                .onCompletion {  Log.d("smarthealth" , "loggedUserChk onCompletion") }
                .catch {   Log.d("smarthealth" , "loggedUserChk catch")}
                .collect{
                    it.let {

                        var category : OHQDeviceCategory = OHQDeviceCategory.Unknown

                            if (it.deviceCategory.contains("BloodPressureMonitor"))
                               category= OHQDeviceCategory.BloodPressureMonitor
                            else if(it.deviceCategory.contains("WeightScale")){
                               category =OHQDeviceCategory.WeightScale
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

                                    _measurementState.value = MeasurementStatus.Success
                                    _measurementText.value = "측정된 혈압은 ${bloodPressure.systolic} 입니다."

                                }
                            }

                            OHQDeviceCategory.BodyCompositionMonitor->{

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
                                        _measurementState.value = MeasurementStatus.Success
                                        _measurementText.value = "측정된 몸무게는 ${bodyComposition.weight} 입니다."
                                    }

                            }else->{

                            }
                        }

                    }catch (e : Exception){
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
                .onStart {   Log.d("smarthealth" , "survey start") }
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
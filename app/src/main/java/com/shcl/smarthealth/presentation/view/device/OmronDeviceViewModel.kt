package com.shcl.smarthealth.presentation.view.device


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.omron.BloodPressure
import com.shcl.smarthealth.domain.model.omron.BodyComposition
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
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
import javax.inject.Inject

@HiltViewModel
class OmronDeviceViewModel @Inject constructor(
    private val omronDeviceUseCase: OmronDeviceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = MutableStateFlow(ScanDeviceState())
    val state = _state.asStateFlow()

    private val _measurementState = MutableStateFlow(MeasurementRecordState(sessionData = null))
    val measurementState = _measurementState.asStateFlow()

    //private val _testState = MutableStateFlow(0)
    //val testState = _testState.asStateFlow()


    init {

        GlobalVariables.coroutineScope = viewModelScope

        /*
        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.testStateFlow(viewModelScope).collect{
                Log.d("test" , "${it}")
                _testState.value = it
            }
        }*/


        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.onScan()
                .onStart { Log.d("sdevice","scan on start") }
                .onCompletion { Log.d("sdevice","scan on completion") }
                .catch { Log.d("sdevice" , "error!!") }
                .collect{finedDevices->
                    finedDevices.let{
                        if(it.isEmpty()){
                            Log.d("sdevice","scan device is empty")
                        }else{
                            Log.d("sdevice","scan device size ${it.size}")
                            _state.value = ScanDeviceState(scannedDevices = it.toMutableList())
                        }
                    }
                }
        }
    }

    fun getMeasurementRecord(device : DiscoveredDevice , type : RequestType){

        viewModelScope.launch {
            omronDeviceUseCase.getBloodPressureUseCase.getDataTransfer(device , type).collect{
                Log.d("omron-s" , it.toString())

                if(it.status == MeasurementStatus.ParingSuccess){
                    _registerToDBDevice(device)
                }else if(it.status == MeasurementStatus.ParingFail){
                    Log.e("omron-s","paring error")
                }

                if((it.status == MeasurementStatus.Success)){

                    val deviceCategory = it.category

                    try{
                        val measurementRecord : Map<OHQMeasurementRecordKey , Any>? = it.sessionData?.measurementRecord?.get(0)
                        when(deviceCategory){
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

                                    //ROOM DB - update
                                    _updateBloodPressure(bloodPressure)
                                    Log.d("omron-s","${bloodPressure}")
                                }
                            }
                            OHQDeviceCategory.WeightScale->{

                                Log.d("omron-s","session WeightScale")

                            }
                            OHQDeviceCategory.PulseOximeter->{}
                            OHQDeviceCategory.HealthThermometer->{}
                            OHQDeviceCategory.Unknown->{}
                            OHQDeviceCategory.BodyCompositionMonitor->{
                                Log.d("omron-s","session BodyCompositionMonitor")

                                try{
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
                                        _updateBodyComposition(bodyComposition)
                                    }
                                }catch(e : Exception){
                                    Log.e("omron-s" , "${e.message}")
                                }

                            }
                            else->{
                                Log.d("omron-s","OHQDeviceCategory error")
                            }
                        }

                    }catch(e : Exception){
                        Log.e("omron-s","${e.message}")
                    }
                }
                _measurementState.value = it
            }
        }
    }

    fun scanDevice(){
        omronDeviceUseCase.scanDeviceUseCase.searchDevices()
    }

    fun stopScan(){
        Log.d("omron", "viewModel - stopDevice")
        omronDeviceUseCase.scanDeviceUseCase.stopDevice()
    }

    /*
    fun paring(discoveredDevice: DiscoveredDevice){
        viewModelScope.launch {
            omronDeviceUseCase.registerDeviceUseCase.(discoveredDevice).collect{
                //paring
                if(it.status == MeasurementStatus.ParingSuccess){
                    _registerToDBDevice(discoveredDevice)
                }else if(it.status == MeasurementStatus.ParingFail){
                    Log.e("omron-s","paring error")
                }
            }
        }
    }*/

    fun _registerToDBDevice(discoveredDevice: DiscoveredDevice){

        GlobalScope.launch(Dispatchers.IO){
            omronDeviceUseCase.registerDeviceUseCase.registerDeviceToDB(discoveredDevice)
        }
    }

    fun _updateBloodPressure(bloodPressure: BloodPressure){
        Log.d("omron" , "update BloodPressure")
        try{

            GlobalScope.launch(Dispatchers.IO){
                omronDeviceUseCase.setBloodPressureUseCase.updateBloodPressureToDB(
                    BloodPressureRoom(
                        userId = 1,
                        diastolic = bloodPressure.diastolic,
                        diastolicUnit = bloodPressure.diastolicUnit,
                        systolic = bloodPressure.systolic,
                        systolicUnit = bloodPressure.systolicUnit,
                        pulseRate = bloodPressure.pulseRate,
                        timeStamp = bloodPressure.timeStamp
                    ))
            }

        }catch(e : Exception){
            Log.d("omron-s" , "${e.message}")
        }
    }

    fun _updateBodyComposition(bodyComposition: BodyComposition){
        Log.d("omron", "update bodyComposition")

        try{
            GlobalScope.launch(Dispatchers.IO){
                omronDeviceUseCase.bodyCompositionUseCase.updateBodyCompositionToDB(
                    BodyCompositionRoom(
                        userId = 1,
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
                    )
                )
            }
        }catch(e : Exception){
            Log.d("omron-s" , "${e.message}")
        }
    }

}
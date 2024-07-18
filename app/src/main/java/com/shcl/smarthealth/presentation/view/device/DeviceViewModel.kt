package com.shcl.smarthealth.presentation.view.device


import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.omron.BloodPressure
import com.shcl.smarthealth.domain.model.omron.BodyComposition
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.usecase.isens.IsensDeviceUseCase
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
class DeviceViewModel @Inject constructor(
    private val omronDeviceUseCase: OmronDeviceUseCase,
    private val isensDeviceUseCase: IsensDeviceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _omronDeviceState = MutableStateFlow(ScanDeviceState())
    val omronDeviceState = _omronDeviceState.asStateFlow()

    private val _isensDeviceState = MutableStateFlow(IsensScanDeviceState())
    val isensDeviceState = _isensDeviceState.asStateFlow()

    private val _omronMeasurementState = MutableStateFlow(MeasurementRecordState(sessionData = null))
    val omronMeasurementState = _omronMeasurementState.asStateFlow()

    private val _iSensMeasurementState = MutableStateFlow(IsensGlucoseRecordState(status = MeasurementStatus.Unknown, records = null))
    val isensMeasurementState = _iSensMeasurementState.asStateFlow()



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


        //omron - scan
        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.onScan()
                .onStart { Log.d("omron","scan on start") }
                .onCompletion { Log.d("omron","scan on completion") }
                .catch { Log.d("omron" , "error!!") }
                .collect{finedDevices->
                    finedDevices.let{
                        if(it.isEmpty()){
                            Log.d("omron","scan device is empty")
                        }else{
                            Log.d("omron","scan device size ${it.size}")
                            _omronDeviceState.value = ScanDeviceState(scannedDevices = it.toMutableList())
                        }
                    }
                }
        }

        //isens - scan
        viewModelScope.launch {
            isensDeviceUseCase.isensScanDeviceUseCase.onScan()
                .onStart { Log.d("isens","scan on start") }
                .onCompletion { Log.d("isens","scan on completion") }
                .catch {  Log.d("isens" , "error!!")}
                .collect{ devices->
                    devices.let{
                        if(devices.isEmpty()){
                            Log.d("isens" , "scan devices is empty")
                        }else{
                            _isensDeviceState.value = IsensScanDeviceState(scannedDevices = devices)
                            Log.d("isens","scan device size : ${devices.size}")

                        }
                    }
                }
        }

        //isens - data
        viewModelScope.launch {
            isensDeviceUseCase.getGlucoseRecordUseCase.getDataTransfer().collect{ it ->
                if(it.status == MeasurementStatus.Success){
                    _iSensMeasurementState.value = it

                    it.records?.let {
                        val lastRecord = it[it.size()]
                        Log.d("isens", "glucose : ${lastRecord.glucoseData}")
                        _updateGlucose(lastRecord)
                    }
                }else if(it.status == MeasurementStatus.Connected)

                else{
                    _iSensMeasurementState.value = it
                }
            }
        }
    }


    // omron - data transfer
    fun getOmronMeasurementRecord(device : DiscoveredDevice , type : RequestType){

        viewModelScope.launch {
            omronDeviceUseCase.getBloodPressureUseCase.getDataTransfer(device , type).collect{
                Log.d("omron" , it.toString())

                if(it.status == MeasurementStatus.ParingSuccess){
                    _registerToDBDevice(device)
                }else if(it.status == MeasurementStatus.ParingFail){
                    Log.e("omron","paring error")
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

                                Log.d("omron","session WeightScale")

                            }
                            OHQDeviceCategory.PulseOximeter->{}
                            OHQDeviceCategory.HealthThermometer->{}
                            OHQDeviceCategory.Unknown->{}
                            OHQDeviceCategory.BodyCompositionMonitor->{
                                Log.d("omron","session BodyCompositionMonitor")

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
                                    Log.e("omron" , "${e.message}")
                                }

                            }
                            else->{
                                Log.d("omron","OHQDeviceCategory error")
                            }
                        }

                    }catch(e : Exception){
                        Log.e("omron","${e.message}")
                    }
                }
                _omronMeasurementState.value = it
            }
        }
    }

    fun omronScanDevice(){
        _omronDeviceState.value = ScanDeviceState()
        omronDeviceUseCase.scanDeviceUseCase.searchDevices()
    }

    fun omronStopScan(){
        Log.d("omron", "viewModel - stopDevice")
        omronDeviceUseCase.scanDeviceUseCase.stopDevice()
    }

    fun isensScanDevice(){
        _isensDeviceState.value = IsensScanDeviceState()
        isensDeviceUseCase.isensScanDeviceUseCase.startScan()
    }

    fun isensStopScan(){
        //var ll : MutableList<BluetoothDevice?> = mutableListOf()
        //ll.add(BluetoothDevice("asdfasdf"))
        //ll.add(0 , BluetoothDevice())
       // _isensDeviceState.value = IsensScanDeviceState(scannedDevices = ll )
        isensDeviceUseCase.isensScanDeviceUseCase.stopScan()
    }

    fun isensConnect(address : String){
        isensDeviceUseCase.isensScanDeviceUseCase.connect(address)
    }

    fun isensAllRecords(){
        isensDeviceUseCase.getGlucoseRecordUseCase.requestAllRecords()
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

    fun _updateGlucose(glucoseRecord: IBLE_GlucoseRecord){

        try{
            GlobalScope.launch(Dispatchers.IO){
                isensDeviceUseCase.setGlucoseRecordUserCase.updateGlucoseRecordToDB(
                    GlucoseRecordRoom(
                        userId = 1,
                        glucoseData = glucoseRecord.glucoseData,
                        flagCs =  glucoseRecord.flag_cs,
                        flagHilow = glucoseRecord.flag_hilow,
                        flagContext = glucoseRecord.flag_context,
                        flagMeal = glucoseRecord.flag_meal,
                        flagFasting = glucoseRecord.flag_fasting,
                        flagKetone = glucoseRecord.flag_ketone,
                        flagNomark = glucoseRecord.flag_nomark,
                        timeOffset = glucoseRecord.timeoffset,
                        time = glucoseRecord.time
                    ))
            }

        }catch(e : Exception){
            Log.e("isens" , e.toString())
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
            Log.d("omron" , "${e.message}")
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
            Log.d("omron" , "${e.message}")
        }
    }

}
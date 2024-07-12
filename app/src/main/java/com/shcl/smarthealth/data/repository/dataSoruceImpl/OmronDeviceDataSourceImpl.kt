package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.common.GlobalVariables.context
import com.shcl.smarthealth.common.ble.controller.BluetoothPowerController
import com.shcl.smarthealth.common.ble.controller.ScanController
import com.shcl.smarthealth.common.ble.controller.SessionController
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.model.omron.ComType
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.Protocol
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.model.omron.ResultType
import com.shcl.smarthealth.domain.model.omron.SessionData
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import com.shcl.smarthealth.presentation.view.device.MeasurementStatus
import jp.co.ohq.ble.OHQDeviceManager
import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQConnectionState
import jp.co.ohq.ble.enumerate.OHQGender
import jp.co.ohq.ble.enumerate.OHQSessionOptionKey
import jp.co.ohq.ble.enumerate.OHQUserDataKey
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


private enum class Mode {
    Normal, UnregisteredUser
}

class OmronDeviceDataSourceImpl @Inject constructor(

) : OmronDeviceDataSource , BluetoothPowerController.Listener{


    private val CONSENT_CODE_OHQ = 0x020E
    private val CONSENT_CODE_UNREGISTERED_USER = 0x0000

    private val USER_INDEX_UNREGISTERED_USER = 0xFF

    private val CONNECTION_WAIT_TIME: Long = 60000
    private val ARG_MODE = "ARG_MODE"
    private val ARG_ADDRESS = "ARG_ADDRESS"
    private val ARG_OPTION = "ARG_OPTION"
    private val ARG_COM_TYPE = "ARG_COM_TYPE"
    private val ARG_PARTIAL_HISTORY_DATA = "ARG_PARTIAL_HISTORY_DATA"
    private val ARG_DISCOVER_DEVICE: String = "ARG_DISCOVER_DEVICE"
    private val ARG_PROTOCOL: String = "ARG_PROTOCOL"

    private var isOnlyPairingMode : Boolean = true
    private lateinit var bluetoothPowerController : BluetoothPowerController
    private lateinit var scanController: ScanController
    private lateinit var sessionController: SessionController
    //private lateinit var listener : EventListener

    var isScanning : Boolean = false
    var isFirstSession : Boolean = true
    var isFirstScan : Boolean = true
    //var mDiscoveredDevices : MutableList<DiscoveredDevice?>

    lateinit var onScanListener : ScanController.Listener
    lateinit var onSessionListener : SessionController.Listener

    init {

        /*
        val onScanListener  = object : ScanController.Listener{
            override fun onScan(discoveredDevices: List<DiscoveredDevice?>) {
                Log.d("sdevice" , "onScan - ${discoveredDevices.size}")
                //onScaned(discoveredDevices)

            }

            override fun onScanCompletion(reason: OHQCompletionReason) {
                isScanning = false
            }
        }*/


        try{
            OHQDeviceManager.init(context)
            bluetoothPowerController = BluetoothPowerController(this)
            //scanController = ScanController(onScanListener)

        }catch (e : Exception){
            Log.e("omron" , "init ${e.message}")
        }


        isScanning = false
        isFirstSession = true
        isFirstScan = true
        //mDiscoveredDevices = mutableListOf()

    }

    override fun startScan() {

        if(isScanning){
            Log.d("omron" , "Already scanning.")
        }

        Log.d("omron" , "omron ble scanning.")

        isScanning = true
        scanController.startScan()

    }

    override fun stopScan() {
       if(!isScanning){
           return
       }

        Log.d("omron" , "Ble scan is stop.")

       // mDiscoveredDevices.clear()
//        mDiscoveredDevices.
        scanController?.let {
            scanController.onPause()
            scanController.stopScan()
            isScanning = false
        }
    }


    override fun onScaned(discoveredDevices : List<DiscoveredDevice?>): Flow<List<DiscoveredDevice?>>{
        return callbackFlow {

            onScanListener  = object : ScanController.Listener{
                override fun onScan(discoveredDevices: List<DiscoveredDevice?>) {
                    Log.d("sdevice" , "onScan - ${discoveredDevices}")
                    //onScaned(discoveredDevices)
                    trySend(discoveredDevices)

                }

                override fun onScanCompletion(reason: OHQCompletionReason) {
                    isScanning = false
                }
            }

            if(isFirstScan){
                scanController = ScanController(onScanListener)
                isFirstScan = false
            }

            awaitClose {
                scanController.stopScan()
            }

        }
    }

    
    override fun onBluetoothStateChanged(enable: Boolean) {
         if(enable){
             Log.d("omron" , "BlueTooth Power on")
         }else{
             Log.d("omron" , "BlueTooth Power off")
         }
    }

    override fun testStateFlow(): Flow<Int> = flow{
        for(i in 1..10000){
            delay(1000)
            emit(i)
        }
    }.stateIn(
        scope = GlobalVariables.coroutineScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(1000)
    )

    override fun getDataTransfer(discoveredDevice: DiscoveredDevice? , rtype :RequestType): Flow<MeasurementRecordState> {
        return callbackFlow<MeasurementRecordState> {

            onSessionListener = object : SessionController.Listener {
                override fun onConnectionStateChanged(connectionState: OHQConnectionState) {
                    Log.d("omron-s","session connect state :  ${connectionState.toString()}")
                    when(connectionState){
                        OHQConnectionState.Connecting->
                            trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Connection))
                        OHQConnectionState.Connected->
                            trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Connected))
                        OHQConnectionState.Disconnected->
                            trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Disconnected))
                        OHQConnectionState.Disconnecting->
                            trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Disconnecting))
                        else->
                            trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Unknown, errorMsg = "OHQConnectionState Unknown"))
                    }
                }

                override fun onSessionComplete(sessionData: SessionData) {
                    Log.d("omron-s","session sessiong data :  ${sessionData}")

                    if(OHQCompletionReason.Canceled == sessionData.completionReason){
                        trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Cancel , errorMsg = "User Cancel"))
                        return
                    }

                    val type : ResultType = _validateSessionWithData(Protocol.BluetoothStandard , sessionData , ComType.Transfer)

                    when(type){
                        ResultType.Success-> {
                            if(rtype == RequestType.Paring){
                                trySend(MeasurementRecordState(sessionData = sessionData,status = MeasurementStatus.ParingSuccess , category = sessionData.deviceCategory ))
                            }else if(rtype == RequestType.DataTransfer){
                                trySend(MeasurementRecordState(sessionData = sessionData,status = MeasurementStatus.Success , category = sessionData.deviceCategory ))
                            }
                        }
                        ResultType.Failure->{
                            if(rtype == RequestType.Paring){
                                trySend(MeasurementRecordState(sessionData = sessionData,status = MeasurementStatus.ParingFail , category = sessionData.deviceCategory))
                            }else if(rtype == RequestType.DataTransfer){
                                trySend(MeasurementRecordState(sessionData = sessionData,status = MeasurementStatus.Fail , category = sessionData.deviceCategory))
                            }
                         }
                    }
                }
            }

            try{
                if(isFirstSession){
                    sessionController = SessionController(onSessionListener)
                    isFirstSession = false
                }
            }catch (e : Exception){
                Log.e("omron-s", "${e.message}")
            }


            discoveredDevice?.let{

                if(discoveredDevice.address.isNullOrEmpty()){
                    trySend(MeasurementRecordState(sessionData = null,status = MeasurementStatus.Error , errorMsg = "device Address is Empty."))
                }

                var option: MutableMap<OHQSessionOptionKey, Any> = HashMap()

                if(rtype == RequestType.Paring){
                    option.put(OHQSessionOptionKey.RegisterNewUserKey , true)
                    option.put(OHQSessionOptionKey.ConsentCodeKey , CONSENT_CODE_OHQ)
                    option.put(OHQSessionOptionKey.UserIndexKey , 1)

                    //userData
                    var userData = HashMap<OHQUserDataKey, Any>()
                    userData.put(OHQUserDataKey.DateOfBirthKey , "1983-12-04")
                    userData.put(OHQUserDataKey.HeightKey , "174.5")
                    userData.put(OHQUserDataKey.GenderKey , OHQGender.Male)

                    option.put(OHQSessionOptionKey.UserDataKey ,userData )
                    option.put(OHQSessionOptionKey.DatabaseChangeIncrementValueKey , 0L)
                    option.put(OHQSessionOptionKey.UserDataUpdateFlagKey , true)
                    option.put(OHQSessionOptionKey.AllowAccessToOmronExtendedMeasurementRecordsKey, true)
                    option.put(OHQSessionOptionKey.ReadMeasurementRecordsKey , true)

                }else if(rtype == RequestType.DataTransfer){
                    // Unregister user session
                    //option.put(OHQSessionOptionKey.UserIndexKey ,1)
                    //option.put(OHQSessionOptionKey.ConsentCodeKey , CONSENT_CODE_UNREGISTERED_USER)
                    //option.put(OHQSessionOptionKey.UserDataKey , HashMap<UserData>())
                    option.put(OHQSessionOptionKey.ReadMeasurementRecordsKey,true)
                    option.put(OHQSessionOptionKey.ConnectionWaitTimeKey , CONNECTION_WAIT_TIME)
                    option.put(OHQSessionOptionKey.AllowAccessToOmronExtendedMeasurementRecordsKey, true)

                    //var userData = emptyMap<OHQUserDataKey, Object>()
                    //option.put(OHQSessionOptionKey.UserDataKey ,userData )
                }

                Log.d("omron-s","Started session.! : ${discoveredDevice.address}")
                sessionController.startSession(discoveredDevice.address , option = option)

            }


            awaitClose {
                sessionController.onPause()
            }

        }
    }

    fun _validateSessionWithData(
        protocol: Protocol,
        sessionData: SessionData,
        type: ComType
    ): ResultType {
        if (OHQCompletionReason.Disconnected != sessionData.completionReason) {
            Log.e("omron","OHQCompletionReason.Disconnected != sessionData.getCompletionReason()")
            return ResultType.Failure
        }
        if (Protocol.OmronExtension === protocol && null == sessionData.userIndex) {
            Log.e("omron","Protocol.OmronExtension == protocol && null == sessionData.getUserIndex()")
            return ResultType.Failure
        }
        if (null == sessionData.batteryLevel && null == sessionData.currentTime && null == sessionData.measurementRecord) {
            return ResultType.Failure
        }
        val option: Map<OHQSessionOptionKey, Any>? = sessionData.option
        if (null == option) {
            Log.e("omron","null == option")
            return ResultType.Failure
        }
        if (option.containsKey(OHQSessionOptionKey.UserDataKey)
            && option.containsKey(OHQSessionOptionKey.DatabaseChangeIncrementValueKey)
        ) {
            if (null != sessionData.userIndex && null == sessionData.databaseChangeIncrement) {
                Log.e("omron","null != sessionData.getUserIndex() && null == sessionData.getDatabaseChangeIncrement()")
                return ResultType.Failure
            }
        }
        /*
        if (OHQDeviceCategory.PulseOximeter == sessionData.deviceCategory && 0 >= sessionData.measurementRecord.size() && type === ComType.Transfer) {
            return ResultType.Failure
        }*/
        return ResultType.Success
    }



}
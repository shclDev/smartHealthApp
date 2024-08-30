package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.forEach
import com.isens.standard.ble.IBLE_Callback
import com.isens.standard.ble.IBLE_Const
import com.isens.standard.ble.IBLE_Device
import com.isens.standard.ble.IBLE_Error
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.isens.standard.ble.IBLE_Manager
import com.isens.standard.ble.IBLE_ScannerServiceParser
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import com.shcl.smarthealth.presentation.view.device.MeasurementStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class IsensDeviceDataSourceImpl  @Inject constructor(

) : IsensDeviceDataSource , IBLE_Callback , LeScanCallback

{

    private var mBleAdapter : BluetoothAdapter?
    private var mBleManager : BluetoothManager?
    private var isScanning : Boolean = false
    private var isBleInit : Boolean = false
    private lateinit var mLocationManager : LocationManager
    private lateinit var mScanCallback : ScanCallback
    private lateinit var mIBLECallback : IBLE_Callback
    private var isConnected = false

    val filters: List<ScanFilter> = listOf()
    val settings: ScanSettings = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).setReportDelay(0).build()


    init {

        mBleManager = GlobalVariables.context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBleAdapter = mBleManager?.adapter
        mLocationManager = GlobalVariables.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(mBleAdapter == null || mBleManager == null){
            isBleInit = false
        }else{
            isBleInit = true
        }

        mScanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                super.onScanResult(callbackType, result)

                result.let {
                    try {
                        //onScaned(device = result.device)

                        if (IBLE_ScannerServiceParser.decodeDeviceAdvData(result.scanRecord!!.bytes)) {
                            //onScaned(result.device)
                            //onScaned(device = result.device)
                            /*
                            if (result.device.bondState == BluetoothDevice.BOND_BONDED) {
                                addScannedDevice(result.device, result.rssi, true)
                            } else {
                                addScannedDevice(result.device, result.rssi, false)
                            }*/
                        } else {

                        }
                    } catch (e: Exception) {
                        Log.e("isense", "${e.message}")
                    }
                }
            }
        }

        //ble permission check
        IBLE_Manager.getInstance().SetCallback(this)
        IBLE_Manager.getInstance().InitSDK(GlobalVariables.context)

    }


    fun _isBleEnabled() : Boolean{
        return isBleInit
    }

    fun _isGpsEnabled() : Boolean{
        if(!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return false
        }

        return true
    }

    override fun startScan() {

        if(isScanning) return


        if(_isBleEnabled() && _isGpsEnabled()){
           try{
             if(mBleAdapter?.state == BluetoothAdapter.STATE_ON){

                 if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     if (ContextCompat.checkSelfPermission(
                             GlobalVariables.context,
                             Manifest.permission.ACCESS_COARSE_LOCATION
                         ) == PackageManager.PERMISSION_GRANTED
                     ) {
                         mBleAdapter?.bluetoothLeScanner?.flushPendingScanResults(mScanCallback)
                         mBleAdapter?.bluetoothLeScanner?.stopScan(mScanCallback)
                         mBleAdapter?.bluetoothLeScanner?.startScan(
                             filters,
                             settings,
                             mScanCallback
                         )
                     }
                 }
             }
           }catch (e : Exception){
               Log.e("isens" , "${e.message}")
           }
            isScanning = true
        }

    }

    override fun stopScan() {
       try{

           if(isScanning){
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                   if (ActivityCompat.checkSelfPermission(
                           GlobalVariables.context,
                           Manifest.permission.BLUETOOTH_SCAN
                       ) != PackageManager.PERMISSION_GRANTED
                   ) {

                   }

                   mBleAdapter?.bluetoothLeScanner?.flushPendingScanResults(mScanCallback)
                   mBleAdapter?.bluetoothLeScanner?.stopScan(mScanCallback)
               }

               isScanning = false
           }

       }catch(e  : Exception){
           Log.e("isens" , "${e.message}")
       }
    }

    override fun onLeScan(device: BluetoothDevice?, rssi: Int, scanRecord: ByteArray?) {
        onScaned(device)
    }


    override fun onScaned(device: BluetoothDevice?): Flow<MutableList<BluetoothDevice?>> {
        return callbackFlow{

            mScanCallback = object : ScanCallback() {
                override fun onScanResult(callbackType: Int, result: ScanResult) {
                    super.onScanResult(callbackType, result)
                    var list : MutableList<BluetoothDevice?> =  mutableListOf()
                    result.let {
                        try {
                            if (IBLE_ScannerServiceParser.decodeDeviceAdvData(result.scanRecord!!.bytes)) {
                                Log.d("isens" , "found isens device : ${result.device.address}")
                                list.add(result.device)
                                trySend(list)
                            } else {

                            }
                        } catch (e: Exception) {
                            Log.e("isens", "${e.message}")
                        }
                    }
                }
            }


            awaitClose {
                stopScan()
                IBLE_Manager.getInstance().DestroySDK()
            }

        }
    }

    override fun onDataTransfer(records: SparseArray<IBLE_GlucoseRecord>?): Flow<IsensGlucoseRecordState> {
        return callbackFlow {

            mIBLECallback = object : IBLE_Callback {
                override fun CallbackInitSDK(version: Int) {
                    Log.d("", "CallbackInitSDK Version : $version")
                    trySend(
                        IsensGlucoseRecordState(
                            status = MeasurementStatus.InitComplete,
                            records = null)
                    )
                }

                override fun CallbackConnectedDevice() {
                    Log.d("", "CallbackConnectedDevice")
                    trySend(
                        IsensGlucoseRecordState(
                            status = MeasurementStatus.Connected,
                            records = null)
                    )
                }

                override fun CallbackDisconnectedDevice() {
                    Log.d("", "CallbackDisconnectedDevice")
                    /*
                    trySend(
                        IsensGlucoseRecordState(
                            status = MeasurementStatus.Disconnected,
                            records = null)
                    )*/
                }

                override fun CallbackRequestTimeSync() {
                    Log.d("", "CallbackRequestTimeSync")
                }

                override fun CallbackRequestRecordsComplete(sparseArray: SparseArray<IBLE_GlucoseRecord>) {
                    Log.d("", "CallbackRequestRecordsComplete")
                        val mRecords = sparseArray
                        if (mRecords == null || mRecords.size() <= 0) {
                            trySend(
                                IsensGlucoseRecordState(
                                status = MeasurementStatus.NoData,
                                 records = mRecords)
                            )
                        }else{
                            trySend(
                                IsensGlucoseRecordState(
                                    status = MeasurementStatus.Success,
                                    records = mRecords)
                            )
                        }
                }


                override fun CallbackReadDeviceInfo(ible_device: IBLE_Device) {
                    Log.d("", "CallbackReadDeviceInfo")
                    val device = ible_device
                }

                override fun CallbackError(ible_error: IBLE_Error) {
                    Log.d("", "CallbackError")
                    trySend(
                        IsensGlucoseRecordState(
                            status = MeasurementStatus.Error,
                            records = null,
                            errorMsg = ible_error.toString()
                            )
                    )
                }
            }

            IBLE_Manager.getInstance().SetCallback(mIBLECallback)

            awaitClose {
                IBLE_Manager.getInstance().DestroySDK()
            }


        }
    }




    override fun connectDevice(address: String) {
        if(address.isNotEmpty()){
            IBLE_Manager.getInstance().ConnectDevice(address)
        }
    }

    override fun requestAllRecord() {
        IBLE_Manager.getInstance().RequestAllRecords()
    }

    override fun disconnected() {
       IBLE_Manager.getInstance().DisconnectDevice()
    }

    override fun destoryDevice() {
        IBLE_Manager.getInstance().DestroySDK()
    }

    override fun CallbackInitSDK(version: Int) {
        Log.d("isens" ,"callbackInitSDK Version : $version")
    }

    override fun CallbackConnectedDevice() {
        Log.d("isens" ,"CallbackConnectedDevice")
        isConnected = true
    }

    override fun CallbackDisconnectedDevice() {
       Log.d("isens","CallbackDisconnectedDevice")
        isConnected = false
    }

    override fun CallbackRequestTimeSync() {
        Log.d("isens","Callback RequestTimeSync")
    }

    override fun CallbackRequestRecordsComplete(records: SparseArray<IBLE_GlucoseRecord>?) {
        Log.d("isens" , "Callback RequestRecrodsComplete")

        records?.forEach{ i: Int, ibleGlucoserecord: IBLE_GlucoseRecord ->
            Log.d("isens" ,"glucose : ${ibleGlucoserecord.glucoseData}" )
        }
        onDataTransfer(records)

    }

    override fun CallbackReadDeviceInfo(device: IBLE_Device?) {
        Log.d("isens","CallbackReadDeviceInfo")

        device?.let{
            Log.d("isens","$device")
        }
    }

    override fun CallbackError(error: IBLE_Error?) {
        Log.d("isens","CallbackError : $error")
    }
}
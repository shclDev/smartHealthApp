package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.util.Log
import android.util.SparseArray
import com.isens.standard.ble.IBLE_Callback
import com.isens.standard.ble.IBLE_Device
import com.isens.standard.ble.IBLE_Error
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.isens.standard.ble.IBLE_Manager
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsensDeviceDataSourceImpl  @Inject constructor(

) : IsensDeviceDataSource , IBLE_Callback

{

    private lateinit var mBleAdapter : BluetoothAdapter
    private lateinit var mBleManager : BluetoothManager
    private var isScanning : Boolean = false

    init {

        //ble permission check

        IBLE_Manager.getInstance().SetCallback(this)
        IBLE_Manager.getInstance().InitSDK(GlobalVariables.context)

    }


    fun isBleEnabled() : Boolean{

        return true
    }

    override fun startScan() {
        TODO("Not yet implemented")
    }

    override fun stopScan() {
        TODO("Not yet implemented")
    }

    override fun onScaned(discoveredDevices: List<DiscoveredDevice?>): Flow<List<DiscoveredDevice?>> {
        TODO("Not yet implemented")
    }

    override fun requestRecordsComplete(records: SparseArray<IBLE_GlucoseRecord>): Flow<IBLE_GlucoseRecord> {
        TODO("Not yet implemented")
    }

    override fun CallbackInitSDK(version: Int) {
        Log.d("isens" ,"callbackInitSDK Version : $version")
    }

    override fun CallbackConnectedDevice() {
        Log.d("isens" ,"CallbackConnectedDevice")
    }

    override fun CallbackDisconnectedDevice() {
       Log.d("isens","CallbackDisconnectedDevice")

    }

    override fun CallbackRequestTimeSync() {
        Log.d("isens","Callback RequestTimeSync")

    }

    override fun CallbackRequestRecordsComplete(records: SparseArray<IBLE_GlucoseRecord>?) {
        TODO("Not yet implemented")
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
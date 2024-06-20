package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.AndroidRuntimeException
import android.util.Log
import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import com.shcl.smarthealth.common.GlobalVariables.context
import com.shcl.smarthealth.common.ble.controller.BluetoothPowerController
import com.shcl.smarthealth.common.ble.controller.ScanController
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import jp.co.ohq.ble.OHQDeviceManager
import jp.co.ohq.ble.OHQDeviceManager.CompletionBlock
import jp.co.ohq.ble.OHQDeviceManager.ScanObserverBlock
import jp.co.ohq.ble.advertising.EachUserData
import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQDeviceInfoKey
import jp.co.ohq.utility.Types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.EventListener
import java.util.LinkedList
import javax.inject.Inject

class OmronDeviceDataSourceImpl @Inject constructor() : OmronDeviceDataSource , BluetoothPowerController.Listener , ScanController.Listener{

    private var isOnlyPairingMode : Boolean = false
    private lateinit var bluetoothPowerController : BluetoothPowerController
    private lateinit var scanController: ScanController
    //private lateinit var listener : EventListener

    var isScanning : Boolean = false
    var discoveredDevices : List<DiscoveredDevice>

    init {

        try{
            OHQDeviceManager.init(context)
            bluetoothPowerController = BluetoothPowerController(this)
            scanController = ScanController(this)

        }catch (e : Exception){
            Log.e("omron" , "init ${e.message}")
        }


        isScanning = false

        discoveredDevices = LinkedList()

    }

    override fun startScan(): List<DiscoveredDevice> {

        if(isScanning){
            Log.d("omron" , "Already scanning.")
        }

        Log.d("omron" , "omron ble scanning.")

        isScanning = true
        scanController.startScan()

        return discoveredDevices

    }

    override fun stopScan() {
       if(!isScanning){
           return
       }

        Log.d("omron" , "Ble scan is stop.")

        scanController.onPause()
        scanController.stopScan()

        isScanning = false

    }

    override fun pairing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registerDevice(): DiscoveredDevice {
        TODO("Not yet implemented")
    }

    fun _onScan(discoveredDevices : List<DiscoveredDevice?>){

        //var deviceList : List<DiscoveredDevice> = LinkedList()

        Log.d("omron" , "device found : ${discoveredDevices.size}")

        if(isOnlyPairingMode){
            discoveredDevices.forEach{ device-> discoveredDevices.plus(device) }
        }else{
            discoveredDevices.plus(discoveredDevices)
        }
    }

    override fun onBluetoothStateChanged(enable: Boolean) {
         if(enable){
             Log.d("omron" , "BlueTooth Power on")
         }else{
             Log.d("omron" , "BlueTooth Power off")
         }
    }

    override fun onScan(discoveredDevices: List<DiscoveredDevice?>) {

        _onScan(discoveredDevices)

    }

    override fun onScanCompletion(reason: OHQCompletionReason) {
        isScanning = false
    }


}
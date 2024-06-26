package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.location.LocationManager
import android.util.AndroidRuntimeException
import android.util.Log
import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import com.shcl.smarthealth.common.GlobalVariables
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import okhttp3.EventListener
import java.util.LinkedList
import javax.inject.Inject

class OmronDeviceDataSourceImpl @Inject constructor(

) : OmronDeviceDataSource , BluetoothPowerController.Listener{

    private var isOnlyPairingMode : Boolean = true
    private lateinit var bluetoothPowerController : BluetoothPowerController
    private lateinit var scanController: ScanController
    //private lateinit var listener : EventListener

    var isScanning : Boolean = false
    //var mDiscoveredDevices : MutableList<DiscoveredDevice?>

    lateinit var onScanListener : ScanController.Listener

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




    override fun onScaned(discoveredDevices : List<DiscoveredDevice?>): Flow<List<DiscoveredDevice?>>   {
        return callbackFlow {

            onScanListener  = object : ScanController.Listener{
                override fun onScan(discoveredDevices: List<DiscoveredDevice?>) {
                    Log.d("sdevice" , "onScan - ${discoveredDevices.size}")
                    //onScaned(discoveredDevices)
                    trySend(discoveredDevices)

                }

                override fun onScanCompletion(reason: OHQCompletionReason) {
                    isScanning = false
                }
            }

            scanController = ScanController(onScanListener)

            awaitClose {
                scanController.stopScan()
            }

        }
    }


    /*
    override fun onScaned(discoveredDevices : List<DiscoveredDevice?>): Flow<List<DiscoveredDevice?>> {

        //Log.d("sdevice" , "onScaned device found : ${discoveredDevices?.size}")

        discoveredDevices?.let{

            Log.d("sdevice" , "onScaned device found : ${discoveredDevices?.size}")

            if(isOnlyPairingMode){
                discoveredDevices?.forEach{ device-> mDiscoveredDevices.add(device) }
            }else{
                mDiscoveredDevices.addAll(discoveredDevices)
            }

            Log.d("sdevice" , "onScaned mDiscoveredDevices found : ${mDiscoveredDevices.size}")
        }

        return flow{
            emit(mDiscoveredDevices)
        }.stateIn(
            scope = GlobalVariables.coroutineScope,
            initialValue = listOf(),
            started = SharingStarted.WhileSubscribed(1000)
        )
    }*/

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

    override fun getBloodPressureData() {


    }

}
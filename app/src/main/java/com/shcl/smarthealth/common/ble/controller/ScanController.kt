package com.shcl.smarthealth.common.ble.controller

import android.util.AndroidRuntimeException
import android.util.Log
import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import jp.co.ohq.ble.OHQDeviceManager
import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQDeviceInfoKey
import jp.co.ohq.utility.Handler
import jp.co.ohq.utility.Types
import java.util.LinkedList

class ScanController(private val mListener: Listener) {
    private val mHandler = Handler()
    private val mOHQDeviceManager = OHQDeviceManager.sharedInstance()
    private val mDiscoveredDevices: LinkedHashMap<String, DiscoveredDevice?> =
        LinkedHashMap<String, DiscoveredDevice?>()
    private var mFilteringDeviceCategory: OHQDeviceCategory? = null
    private var mIsScanning = false
    private val mBatchedScanRunnable: Runnable = object : Runnable {
        override fun run() {
            _onBatchedScan(LinkedList(mDiscoveredDevices.values))
            mHandler.postDelayed(this, BATCHED_SCAN_INTERVAL)
        }
    }
    private var mHasRestartRequest = false

    fun onResume() {
    }

    fun onPause() {
    }

    fun setFilteringDeviceCategory(deviceCategory: OHQDeviceCategory?) {
        mHandler.post { _setFilteringDeviceCategory(deviceCategory) }
    }

    fun startScan() {
        mHandler.post { _startScan(mFilteringDeviceCategory) }
    }

    fun stopScan() {
        mHandler.post { _stopScan() }
    }

    private fun _setFilteringDeviceCategory(deviceCategory: OHQDeviceCategory?) {
        if (null != deviceCategory) {
            Log.d("omron","device category : ${deviceCategory.name}")
        } else {
            Log.d("omron","null")
        }


        mFilteringDeviceCategory = deviceCategory
        if (mIsScanning) {
            mHasRestartRequest = true
            _stopScan()
        }
    }

    private fun _startScan(filteringDeviceCategory: OHQDeviceCategory?) {


        if (mIsScanning) {
            Log.d("omron","Already scanning.")
            return
        }

        val scanFilter: MutableList<OHQDeviceCategory> = ArrayList()
        if (null != filteringDeviceCategory) {
            Log.d("omron","filteringDeviceCategory:$filteringDeviceCategory")
            scanFilter.add(filteringDeviceCategory)
        }

        mIsScanning = true

        mOHQDeviceManager.scanForDevicesWithCategories(
            scanFilter,
            object : OHQDeviceManager.ScanObserverBlock {
                override fun onDiscoveredDevice(deviceInfo: Map<OHQDeviceInfoKey, Any>) {
                    mHandler.post {
                        _onScan(deviceInfo)
                    }
                }
            },
            object : OHQDeviceManager.CompletionBlock {
                override fun onSessionComplete(reason: OHQCompletionReason) {
                    mHandler.post {
                        _onScanCompletion(reason)
                    }
                }
            }
        )

        mDiscoveredDevices.clear()
        mHandler.postDelayed(mBatchedScanRunnable, BATCHED_SCAN_INTERVAL)

        /*
        mOHQDeviceManager.scanForDevicesWithCategories(
            scanFilter,
            { deviceInfo -> mHandler.post {
                Log.d("omron" , "${deviceInfo}")
                _onScan(deviceInfo)
            }
            },
            { reason -> mHandler.post { _onScanCompletion(reason) } })


        mDiscoveredDevices.clear()
        mHandler.postDelayed(mBatchedScanRunnable, BATCHED_SCAN_INTERVAL)*/
    }

    private fun _stopScan() {

        if (!mIsScanning) {
            return
        }

        mOHQDeviceManager.stopScan()
    }

    private fun _onScan(deviceInfo: Map<OHQDeviceInfoKey, Any>) {
        if (!mIsScanning) {
            Log.d("omron","Scanning is stopped.")
            return
        }

        Log.d("omron" , deviceInfo.toString())

        //Log.d("omron","Scanning in ScanController.")

        val address: String
        if (!deviceInfo.containsKey(OHQDeviceInfoKey.AddressKey)) {
            throw AndroidRuntimeException("The address must be present.")
        }
        if (null == (Types.autoCast<String>(deviceInfo[OHQDeviceInfoKey.AddressKey]).also {
                address = it
            })) {
            throw AndroidRuntimeException("The address must be present.")
        }

        var discoveredDevice: DiscoveredDevice?
        if (mDiscoveredDevices.containsKey(address)) {
            //Log.d("omron","\"Update discovered device. $address")
            discoveredDevice = mDiscoveredDevices[address]
        } else {
            Log.d("omron","New discovered device. $address")
            discoveredDevice = DiscoveredDevice(address = address)
        }

        if (deviceInfo.containsKey(OHQDeviceInfoKey.AdvertisementDataKey)) {
            val advertisementData = Types.autoCast<List<ADStructure>>(
                deviceInfo[OHQDeviceInfoKey.AdvertisementDataKey]
            )
            discoveredDevice?.advertisementData = advertisementData
        }
        if (deviceInfo.containsKey(OHQDeviceInfoKey.CategoryKey)) {
            val deviceCategory =
                Types.autoCast<OHQDeviceCategory>(deviceInfo[OHQDeviceInfoKey.CategoryKey])
            discoveredDevice?.deviceCategory = deviceCategory
        }
        if (deviceInfo.containsKey(OHQDeviceInfoKey.RSSIKey)) {
            val rssi = Types.autoCast<Int>(deviceInfo[OHQDeviceInfoKey.RSSIKey])
            discoveredDevice?.rssi = rssi
        }
        if (deviceInfo.containsKey(OHQDeviceInfoKey.ModelNameKey)) {
            val modelName = Types.autoCast<String>(deviceInfo[OHQDeviceInfoKey.ModelNameKey])
            discoveredDevice?.modelName = modelName
        }
        if (deviceInfo.containsKey(OHQDeviceInfoKey.LocalNameKey)) {
            val localName = Types.autoCast<String>(deviceInfo[OHQDeviceInfoKey.LocalNameKey])
            discoveredDevice?.localName = localName
        }

        mDiscoveredDevices[address] = discoveredDevice
    }

    private fun _onScanCompletion(reason: OHQCompletionReason) {


        mHandler.removeCallbacks(mBatchedScanRunnable)
        mIsScanning = false

        if (mHasRestartRequest) {
            mHasRestartRequest = false
            _startScan(mFilteringDeviceCategory)
        } else {
            mListener.onScanCompletion(reason)
        }
    }

    private fun _onBatchedScan(discoveredDevices: List<DiscoveredDevice?>) {
        if (!mIsScanning) {
            Log.d("omron","Scanning is stopped.")
            return
        }

        //Log.d("omron" , "${discoveredDevices.size}")
        Log.d("omron","scanController discovered Devices : ${discoveredDevices}")

        mListener.onScan(discoveredDevices)
    }

    interface Listener {
        fun onScan(discoveredDevices: List<DiscoveredDevice?>)
        fun onScanCompletion(reason: OHQCompletionReason)
    }

    companion object {
        private const val BATCHED_SCAN_INTERVAL: Long = 1000
    }
}
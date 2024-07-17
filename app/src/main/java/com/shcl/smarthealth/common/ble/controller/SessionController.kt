package com.shcl.smarthealth.common.ble.controller

import android.os.Bundle
import android.util.Log
import com.shcl.smarthealth.domain.model.omron.SessionData
import jp.co.ohq.androidcorebluetooth.AndroidPeripheral
import jp.co.ohq.ble.OHQDeviceManager
import jp.co.ohq.ble.OHQDeviceManager.CompletionBlock
import jp.co.ohq.ble.OHQDeviceManager.ConnectionObserverBlock
import jp.co.ohq.ble.OHQDeviceManager.DataObserverBlock
import jp.co.ohq.ble.OHQDeviceManager.DebugMonitor
import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQConnectionState
import jp.co.ohq.ble.enumerate.OHQDataType
import jp.co.ohq.ble.enumerate.OHQDetailedState
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQMeasurementRecordKey
import jp.co.ohq.ble.enumerate.OHQSessionOptionKey
import jp.co.ohq.ble.enumerate.OHQUserDataKey
import jp.co.ohq.utility.Handler
import jp.co.ohq.utility.SynchronizeCallback
import jp.co.ohq.utility.Types
import java.util.LinkedList


class SessionController @JvmOverloads constructor(
    listener: Listener,
    debugMonitor: DebugMonitor? = null
) :
    DataObserverBlock, ConnectionObserverBlock, CompletionBlock, DebugMonitor {
    private var mSessionData: SessionData = SessionData()
    private val mHandler: Handler
    private val mListener: Listener
    private val mDebugMonitor: DebugMonitor?
    private val mOHQDeviceManager: OHQDeviceManager
    private var mSessionAddress: String? = null

    init {

        mHandler = Handler()
        mListener = listener
        mDebugMonitor = debugMonitor
        mOHQDeviceManager = OHQDeviceManager.sharedInstance()
    }

    fun onResume() {
    }

    fun onPause() {
    }

    val isInSession: Boolean
        get() {
            val ret: Boolean
            if (mHandler.isCurrentThread) {
                ret = null != mSessionAddress
            } else {
                val callback = SynchronizeCallback()
                mHandler.post {
                    callback.result = null != mSessionAddress
                    callback.unlock()
                }
                callback.lock()
                ret = Types.autoCast(callback.result)
            }
            return ret
        }

    fun setConfig(config: Bundle) {
        mHandler.post { mOHQDeviceManager.setConfig(config) }
    }

    fun startSession(
        address: String,
        option: Map<OHQSessionOptionKey, Any>
    ) {
        mHandler.post { _startSession(address, option) }
    }

    fun cancel() {
        mHandler.post { _cancelSession() }
    }

    override fun onDataReceived(aDataType: OHQDataType, aData: Any) {
        mHandler.post { _onDataReceived(aDataType, aData) }
    }

    override fun onConnectionStateChanged(aState: OHQConnectionState) {
        mHandler.post { _onConnectionStateChanged(aState) }
    }

    override fun onSessionComplete(aReason: OHQCompletionReason) {
        mHandler.post { _onSessionComplete(aReason) }
    }

    override fun onDetailedStateChanged(newState: OHQDetailedState) {

        mHandler.post { mDebugMonitor?.onDetailedStateChanged(newState) }
    }

    override fun onPairingRequest() {

        mHandler.post { mDebugMonitor?.onPairingRequest() }
    }

    override fun onBondStateChanged(bondState: AndroidPeripheral.BondState) {

        mHandler.post { mDebugMonitor?.onBondStateChanged(bondState) }
    }

    override fun onAclConnectionStateChanged(aclConnectionState: AndroidPeripheral.AclConnectionState) {

        mHandler.post { mDebugMonitor?.onAclConnectionStateChanged(aclConnectionState) }
    }

    override fun onGattConnectionStateChanged(gattConnectionState: AndroidPeripheral.GattConnectionState) {

        mHandler.post { mDebugMonitor?.onGattConnectionStateChanged(gattConnectionState) }
    }

    private fun _startSession(
        address: String,
        option: Map<OHQSessionOptionKey, Any>
    ) {

        if (null != mSessionAddress) {
            Log.e("omron","null != mSessionAddress")
            return
        }
        mSessionData.option = option
        mOHQDeviceManager.startSessionWithDevice(
            address,
            this,
            this,
            this,
            option,
            this
        )
        mSessionAddress = address
    }

    private fun _cancelSession() {

        if (null == mSessionAddress) {
            Log.d("omron","null == mSessionAddress")
            return
        }
        mOHQDeviceManager.cancelSessionWithDevice(mSessionAddress!!)
        mSessionAddress = null
    }

    private fun _onDataReceived(dataType: OHQDataType, data: Any) {
        when (dataType) {
            OHQDataType.DeviceCategory -> {
                val deviceCategory = Types.autoCast<OHQDeviceCategory>(data)
                Log.d("omron",dataType.name + " " + deviceCategory.name)
                //mSessionData.setDeviceCategory(deviceCategory)
                mSessionData.deviceCategory = deviceCategory
            }

            OHQDataType.ModelName -> {
                val modelName = Types.autoCast<String>(data)
                Log.d("omron",dataType.name + " " + modelName)
                mSessionData.modelName = modelName
            }

            OHQDataType.CurrentTime -> {
                val currentTime = Types.autoCast<String>(data)
                Log.d("omron",dataType.name + " " + currentTime)
                mSessionData.currentTime = currentTime
            }

            OHQDataType.BatteryLevel -> {
                val batteryLevel = Types.autoCast<Int>(data)
                Log.d("omron",dataType.name + " " + batteryLevel)
                mSessionData.batteryLevel = (data as Int)
            }

            OHQDataType.RegisteredUserIndex -> {
                val registeredUserIndex = Types.autoCast<Int>(data)
                Log.d("omron",dataType.name + " " + registeredUserIndex)
                mSessionData.userIndex = registeredUserIndex
            }

            OHQDataType.AuthenticatedUserIndex -> {
                val authenticatedUserIndex = Types.autoCast<Int>(data)
                Log.d("omron",dataType.name + " " + authenticatedUserIndex)
                mSessionData.userIndex = authenticatedUserIndex
            }

            OHQDataType.DeletedUserIndex -> {
                val deletedUserIndex = Types.autoCast<Int>(data)
                Log.d("omron",dataType.name + " " + deletedUserIndex)
                mSessionData.userIndex = deletedUserIndex
            }

            OHQDataType.UserData -> {
                val userData = Types.autoCast<Map<OHQUserDataKey, Any>>(data)
                Log.d("omron",dataType.name + " " + userData.toString())
                mSessionData.userData = userData
            }

            OHQDataType.DatabaseChangeIncrement -> {
                val databaseChangeIncrement = Types.autoCast<Long>(data)
                Log.d("omron",dataType.name + " " + databaseChangeIncrement)
                mSessionData.databaseChangeIncrement = databaseChangeIncrement
            }

            OHQDataType.SequenceNumberOfLatestRecord -> {
                val sequenceNumberOfLatestRecord = Types.autoCast<Int>(data)
                Log.d("omron",dataType.name + " " + sequenceNumberOfLatestRecord)
                mSessionData.sequenceNumberOfLatestRecord = sequenceNumberOfLatestRecord
            }

            OHQDataType.MeasurementRecords -> {
                val measurementRecords =
                    Types.autoCast<LinkedList<Map<OHQMeasurementRecordKey, Any>>>(data)
                Log.d("omron",dataType.name + " " + measurementRecords.toString())
                mSessionData.measurementRecord = measurementRecords
            }

            else -> {}
        }
    }

    private fun _onConnectionStateChanged(connectionState: OHQConnectionState) {

        mListener.onConnectionStateChanged(connectionState)
    }

    private fun _onSessionComplete(completionReason: OHQCompletionReason) {

        mSessionAddress = null
        mSessionData.completionReason = completionReason
        mListener.onSessionComplete(mSessionData)
    }

    interface Listener {
        fun onConnectionStateChanged(connectionState: OHQConnectionState)

        fun onSessionComplete(sessionData: SessionData)
    }
}
package com.shcl.smarthealth.common.ble.controller

import jp.co.ohq.ble.OHQDeviceManager
import jp.co.ohq.ble.enumerate.OHQDeviceManagerState
import jp.co.ohq.utility.Handler

class BluetoothPowerController constructor(_listener : Listener) {

    private val mHandler: Handler? = Handler()
    private val mListener = _listener
    private var mState = false


    init {
        mState = _convertState(OHQDeviceManager.sharedInstance().state())

        if(mState){
            OHQDeviceManager.sharedInstance().setStateMonitor { state ->
                mHandler?.post {
                    _onStateChanged(
                        state
                    )
                }
            }
        }

    }
    fun state(): Boolean {
        return mState
    }

    fun onResume() {
        mState = _convertState(OHQDeviceManager.sharedInstance().state())
        OHQDeviceManager.sharedInstance().setStateMonitor { state ->
            mHandler?.post {
                _onStateChanged(
                    state
                )
            }
        }
    }

    fun onPause() {
        OHQDeviceManager.sharedInstance().setStateMonitor(null)
    }

    private fun _onStateChanged(state: OHQDeviceManagerState) {
        if (mState == _convertState(state)) {
            return
        }
        mState = !mState
        mListener.onBluetoothStateChanged(mState)
    }

    private fun _convertState(state: OHQDeviceManagerState): Boolean {
        val enable = when (state) {
            OHQDeviceManagerState.PoweredOn -> true
            OHQDeviceManagerState.PoweredOff -> false
            else -> false
        }
        return enable
    }

    interface Listener {
        fun onBluetoothStateChanged(enable: Boolean)
    }
}
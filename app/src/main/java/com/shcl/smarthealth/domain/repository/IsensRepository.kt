package com.shcl.smarthealth.domain.repository

import android.bluetooth.BluetoothDevice
import android.util.SparseArray
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import kotlinx.coroutines.flow.Flow

interface IsensRepository {

    fun requestTimeSync()
    fun requestAllRecords()
    fun requestRecordAfterSequence(sequence : Int)
    fun connect(address : String)
    fun disconnectDevice()
    fun unPairDevice(address : String)
    fun destorySDK()
    fun onScan() : Flow<MutableList<BluetoothDevice?>>
    fun onDataTransfer(records: SparseArray<IBLE_GlucoseRecord>?) : Flow<IsensGlucoseRecordState>
    fun stopScan()
    fun startScan()

}
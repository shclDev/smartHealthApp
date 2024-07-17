package com.shcl.smarthealth.data.repository.dataSource

import android.bluetooth.BluetoothDevice
import android.util.SparseArray
import com.isens.standard.ble.IBLE_Device
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import kotlinx.coroutines.flow.Flow

interface IsensDeviceDataSource {

    fun startScan()
    fun stopScan()

    fun onScaned(device: BluetoothDevice?) : Flow<MutableList<BluetoothDevice?>>

    fun onDataTransfer(records: SparseArray<IBLE_GlucoseRecord>?) : Flow<IsensGlucoseRecordState>
    fun requestRecordsComplete(records : SparseArray<IBLE_GlucoseRecord>) : Flow<IBLE_GlucoseRecord>

    fun connectDevice(address : String)

    fun requestAllRecord()

}
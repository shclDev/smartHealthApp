package com.shcl.smarthealth.data.repository.dataSource

import android.util.SparseArray
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.flow.Flow

interface IsensDeviceDataSource {

    fun startScan()
    fun stopScan()

    fun onScaned(discoveredDevices: List<DiscoveredDevice?>) : Flow<List<DiscoveredDevice?>>

    fun requestRecordsComplete(records : SparseArray<IBLE_GlucoseRecord>) : Flow<IBLE_GlucoseRecord>


}
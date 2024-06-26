package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface OmronDeviceDataSource {
    fun startScan()
    fun stopScan()

    fun pairing() :Boolean

    fun registerDevice() : DiscoveredDevice

    fun onScaned(discoveredDevices: List<DiscoveredDevice?>) : Flow<List<DiscoveredDevice?>>

    fun testStateFlow() : Flow<Int>

    fun getBloodPressureData()

}
package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.flow.Flow

interface OmronDeviceDataSource {
    fun startScan() : List<DiscoveredDevice>
    fun stopScan()

    fun pairing() :Boolean

    fun registerDevice() : DiscoveredDevice

}
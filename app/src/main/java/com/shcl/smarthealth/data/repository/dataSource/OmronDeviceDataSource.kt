package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface OmronDeviceDataSource {
    fun startScan()
    fun stopScan()

    //fun pairing(discoveredDevice: DiscoveredDevice) : Flow<MeasurementRecordState>

    fun onScaned(discoveredDevices: List<DiscoveredDevice?>) : Flow<List<DiscoveredDevice?>>

    fun testStateFlow() : Flow<Int>

    fun getDataTransfer(discoveredDevice: DiscoveredDevice? , type : RequestType) : Flow<MeasurementRecordState>

}
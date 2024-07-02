package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow

interface OmronRepository {

    suspend fun connect()

    fun searchDevice()

    fun getBloodPressureData(discoveredDevice: DiscoveredDevice?) : Flow<MeasurementRecordState>

    suspend fun getBodyData()

    suspend fun pairing()

    fun stopScan()

     fun onScan() : Flow<List<DiscoveredDevice?>>

     fun testStateFlow() : Flow<Int>

}
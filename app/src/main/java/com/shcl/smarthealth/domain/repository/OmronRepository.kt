package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.flow.Flow

interface OmronRepository {

    suspend fun connect()

    fun searchDevice()

    suspend fun getBloodPressureData()

    suspend fun getBodyData()

    suspend fun pairing()

    fun stopScan()

     fun onScan() : Flow<List<DiscoveredDevice?>>

     fun testStateFlow() : Flow<Int>

}
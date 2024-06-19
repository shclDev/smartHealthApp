package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import kotlinx.coroutines.flow.Flow

interface OmronRepository {

    suspend fun connect()

    suspend fun searchDevice() : Flow<List<DiscoveredDevice>>

    suspend fun getBloodPressureData()

    suspend fun getBodyData()

    suspend fun pairing()

}
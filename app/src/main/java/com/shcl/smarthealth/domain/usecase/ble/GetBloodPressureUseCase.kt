package com.shcl.smarthealth.domain.usecase.ble

import com.shcl.smarthealth.common.Resource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBloodPressureUseCase @Inject constructor(
    private val repository: OmronRepository
) {


    suspend fun getBloodPressureData(discoveredDevice: DiscoveredDevice ) {
        repository.getBloodPressureData()
    }

    //operator fun invoke() : Flow<Resource<>>





}
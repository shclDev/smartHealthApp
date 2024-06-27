package com.shcl.smarthealth.domain.usecase.ble


import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBloodPressureUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    suspend fun getBloodPressureData(discoveredDevice: DiscoveredDevice? ) :Flow<MeasurementRecordState> {
        return repository.getBloodPressureData(discoveredDevice)
    }

}
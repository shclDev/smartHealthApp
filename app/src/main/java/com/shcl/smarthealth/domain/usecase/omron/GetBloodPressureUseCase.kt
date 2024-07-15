package com.shcl.smarthealth.domain.usecase.omron


import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBloodPressureUseCase @Inject constructor(
    private val repository: OmronRepository
) {

     fun getDataTransfer(discoveredDevice: DiscoveredDevice?, type : RequestType ) :Flow<MeasurementRecordState> {
        return repository.getDataTransfer(discoveredDevice , type)
    }

}
package com.shcl.smarthealth.domain.usecase.ble

import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.omron.BodyComposition
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class BodyCompositionUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom) = repository.updateBodyCompositionDataToDB(bodyCompositionRoom)

}
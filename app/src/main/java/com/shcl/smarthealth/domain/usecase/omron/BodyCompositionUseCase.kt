package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class BodyCompositionUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom) = repository.updateBodyCompositionDataToDB(bodyCompositionRoom)

}
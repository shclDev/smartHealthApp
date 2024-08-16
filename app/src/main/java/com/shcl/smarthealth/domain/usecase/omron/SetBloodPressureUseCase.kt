package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class SetBloodPressureUseCase @Inject constructor(
    private val repository : OmronRepository
) {

    suspend fun updateBloodPressureToDB( bloodPressureRoom: BloodPressureRoom) = repository.updateBloodPressureDataToDB(bloodPressureRoom)

}
package com.shcl.smarthealth.domain.usecase.isens

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class SetGlucoseRecordUserCase @Inject constructor(
    private val repository : IsensRepository
) {
    suspend fun updateGlucoseRecordToDB( glucoseRecordRoom: GlucoseRecordRoom) = repository.updateGlucoseRecordToDB(glucoseRecordRoom)
    fun getGlucoseRecordFromDB(userId : Int) = repository.getGlucoseRecordFromDB(userId)
}
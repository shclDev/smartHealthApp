package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import kotlinx.coroutines.flow.Flow

interface MeasureRecordDataSource {
    fun getBloodPressureFromDB(userId : Int): Flow<BloodPressureRoom>
    suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom)
}
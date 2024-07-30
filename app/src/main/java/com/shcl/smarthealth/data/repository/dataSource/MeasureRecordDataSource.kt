package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import kotlinx.coroutines.flow.Flow

interface MeasureRecordDataSource {
    fun getBloodPressureFromDB(userId : Int): Flow<BloodPressureRoom>
    suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom)
    suspend fun registerDeviceToDB(foundDeviceRoom: FoundDeviceRoom)
    fun getAllRegisterDevices() : Flow<List<FoundDeviceRoom>>
    suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom)
    fun getBodyCompositionFromDB(userId : Int) : Flow<BodyCompositionRoom>
    suspend fun updateGlucoseToDB(glucoseRecordRoom: GlucoseRecordRoom)
    fun getGlucoseFromDB(userId : Int) : Flow<GlucoseRecordRoom>
    suspend fun updateUser(userRoom: UserRoom)
    fun getUserFromDB(userId : Int) : Flow<UserRoom>
}
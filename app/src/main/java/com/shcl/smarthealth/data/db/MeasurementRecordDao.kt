package com.shcl.smarthealth.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBloodPressure(bloodPressure:BloodPressureRoom )

    @Query("SELECT * FROM blood_pressure_tb")
    fun getAllBloodPressure() : List<BloodPressureRoom>

    @Query("SELECT * FROM blood_pressure_tb WHERE userId = :userId ORDER BY timeStamp DESC LIMIT 1")
    fun getBloodPressureByUserID(userId : Int) : Flow<BloodPressureRoom>

    @Query("DELETE FROM blood_pressure_tb")
    suspend fun deleteAllBloodPressure()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDevice( foundDeviceRoom: FoundDeviceRoom)

    @Query("SELECT * FROM found_device_tb")
    fun getAllDevice() : Flow<List<FoundDeviceRoom>>

    @Query("SELECT * FROM found_device_tb WHERE userId = :userId ORDER BY timeStamp DESC LIMIT 1")
    fun getDeviceByUserID(userId : Int) : Flow<FoundDeviceRoom>

    @Query("SELECT * FROM found_device_tb WHERE deviceCategory = :deviceCategory ORDER BY timeStamp DESC LIMIT 1")
    fun getDeviceByCategory(deviceCategory : String) : Flow<FoundDeviceRoom>

    @Query("DELETE FROM found_device_tb")
    suspend fun deleteAllDevice()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBodyComposition(bodyCompositionRoom: BodyCompositionRoom)

    @Query("SELECT * FROM body_composition_tb")
    fun getAllBodyComposition() : List<BodyCompositionRoom>

    @Query("SELECT * FROM body_composition_tb WHERE userId = :userId ORDER BY timeStamp DESC LIMIT 1")
    fun getBodyCompositionByUserID(userId : Int) : Flow<BodyCompositionRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGlucoseRecord(glucoseRecordRoom: GlucoseRecordRoom)

    @Query("SELECT * FROM glucose_record_tb")
    fun getAllGlucoseRecord() : List<GlucoseRecordRoom>

    @Query("SELECT * FROM glucose_record_tb WHERE userId = :userId ORDER BY time DESC LIMIT 1")
    fun getGlucoseRecordByUserID(userId : Int) : Flow<GlucoseRecordRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userRoom: UserRoom)

    @Query("SELECT * FROM user_tb WHERE userId = :userId ")
    fun getUserByUserID(userId : Int) : Flow<UserRoom>

    @Query("SELECT * FROM user_tb")
    fun getAllUser() : Flow<List<UserRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLastedLoginUser(lastedLoginUserRoom: LastedLoginUserRoom)

    @Query("SELECT * FROM lasted_login_user_tb ORDER BY loginTime DESC LIMIT 1 ")
    fun getLastedLoginUserByUserID() : Flow<LastedLoginUserRoom>


}
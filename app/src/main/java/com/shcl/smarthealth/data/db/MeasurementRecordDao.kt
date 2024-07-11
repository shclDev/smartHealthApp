package com.shcl.smarthealth.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
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

    @Query("DELETE FROM found_device_tb")
    suspend fun deleteAllDevice()

}
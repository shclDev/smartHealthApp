package com.shcl.smarthealth.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBloodPressure(bloodPressure:BloodPressureRoom )

    @Query("SELECT * FROM bloodPressure")
    fun getAllBloodPressure() : List<BloodPressureRoom>

    @Query("SELECT * FROM bloodPressure WHERE userId = :userId")
    fun getBloodPressureByUserID(userId : Int) : Flow<BloodPressureRoom>

    @Query("DELETE FROM bloodPressure")
    suspend fun deleteAllBloodPressure()

}
package com.shcl.smarthealth.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom

@Database(
    entities = [BloodPressureRoom::class , FoundDeviceRoom::class],
    version = 1,
    exportSchema = false
)
abstract class MeasurementRecordDB : RoomDatabase() {
    abstract fun measurementRecordDao() : MeasurementRecordDao
}
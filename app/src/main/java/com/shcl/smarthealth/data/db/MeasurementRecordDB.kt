package com.shcl.smarthealth.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom

@Database(
    entities = [BloodPressureRoom::class],
    version = 1,
    exportSchema = false
)
abstract class MeasurementRecordDB : RoomDatabase() {
    abstract fun measurementRecordDao() : MeasurementRecordDao
}
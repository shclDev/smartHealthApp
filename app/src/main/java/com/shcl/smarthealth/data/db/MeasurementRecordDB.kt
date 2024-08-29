package com.shcl.smarthealth.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.TutorialRoom
import com.shcl.smarthealth.domain.model.db.UserRoom

@Database(
    entities = [BloodPressureRoom::class , FoundDeviceRoom::class ,
        BodyCompositionRoom::class , GlucoseRecordRoom::class , UserRoom::class,
        LastedLoginUserRoom::class ,
        TutorialRoom::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDB : RoomDatabase() {
    abstract fun localDBDao() : LocalDBDao
}
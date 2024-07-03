package com.shcl.smarthealth.di

import android.app.Application
import androidx.room.Room
import com.shcl.smarthealth.data.db.MeasurementRecordDB
import com.shcl.smarthealth.data.db.MeasurementRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app : Application) : MeasurementRecordDB =
        Room.databaseBuilder(app , MeasurementRecordDB::class.java ,"measurement_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideMeasurementRecordDao(measurementRecordDB: MeasurementRecordDB) : MeasurementRecordDao =measurementRecordDB.measurementRecordDao()

}
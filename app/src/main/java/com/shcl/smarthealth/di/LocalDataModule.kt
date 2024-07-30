package com.shcl.smarthealth.di

import android.content.Context
import com.shcl.smarthealth.data.db.MeasurementRecordDao
import com.shcl.smarthealth.data.repository.dataSoruceImpl.IsensDeviceDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSoruceImpl.MeasureRecordDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSoruceImpl.OmronDeviceDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSoruceImpl.UserRemoteDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.ohq.ble.OHQDeviceManager


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    fun provideOmronDataSource() : OmronDeviceDataSource =
        OmronDeviceDataSourceImpl()

    @Provides
    fun provideIsensDataSource() : IsensDeviceDataSource =
        IsensDeviceDataSourceImpl()

    @Provides
    fun provideOHQDeviceManager(@ApplicationContext context : Context) : OHQDeviceManager = OHQDeviceManager.init(context)


    @Provides
    fun provideLocalDbDataSource(measurementRecordDao: MeasurementRecordDao ) : MeasureRecordDataSource =
        MeasureRecordDataSourceImpl(measurementRecordDao = measurementRecordDao)

}
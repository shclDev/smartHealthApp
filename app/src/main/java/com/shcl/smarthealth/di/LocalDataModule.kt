package com.shcl.smarthealth.di

import android.content.Context
import com.shcl.smarthealth.data.repository.dataSoruceImpl.OmronDeviceDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.ohq.ble.OHQDeviceManager


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    fun provideLocalDataSource() : OmronDeviceDataSource =
        OmronDeviceDataSourceImpl()

    @Provides
    fun provideOHQDeviceManager(@ApplicationContext context : Context) : OHQDeviceManager = OHQDeviceManager.init(context)



}
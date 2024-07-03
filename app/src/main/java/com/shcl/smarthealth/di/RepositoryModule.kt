package com.shcl.smarthealth.di

import com.shcl.smarthealth.data.repository.DashBoardRepositoryImpl
import com.shcl.smarthealth.data.repository.OmronRepositoryImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDashBoardRepository(
        dashBoardRemoteDataSource: DashBoardRemoteDataSource
    ): DashBoardRepository =
        DashBoardRepositoryImpl(dashBoardRemoteDataSource)

    @Provides
    fun provideOmronRepository(
        omronDeviceDataSource: OmronDeviceDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : OmronRepository = OmronRepositoryImpl(omronDeviceDataSource , measureRecordDataSource)
}
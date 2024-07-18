package com.shcl.smarthealth.di

import com.shcl.smarthealth.data.repository.DashBoardRepositoryImpl
import com.shcl.smarthealth.data.repository.IsensRepositoryImpl
import com.shcl.smarthealth.data.repository.OmronRepositoryImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDashBoardRepository(
        dashBoardRemoteDataSource: DashBoardRemoteDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ): DashBoardRepository =
        DashBoardRepositoryImpl(dashBoardRemoteDataSource , measureRecordDataSource)

    @Provides
    fun provideOmronRepository(
        omronDeviceDataSource: OmronDeviceDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : OmronRepository = OmronRepositoryImpl(omronDeviceDataSource , measureRecordDataSource)

    @Provides
    fun provideIsensRepository(
        isensDeviceDataSource: IsensDeviceDataSource,
        measureRecordDataSource: MeasureRecordDataSource
    ) : IsensRepository = IsensRepositoryImpl(isensDeviceDataSource , measureRecordDataSource )
}
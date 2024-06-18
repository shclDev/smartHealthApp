package com.shcl.smarthealth.di


import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.repository.dataSoruceImpl.DashBoardRemoteDataSourceImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideDashBoardRemoteDataSource(dashBoardApi: DashBoardApi) : DashBoardRemoteDataSource =
        DashBoardRemoteDataSourceImpl(dashBoardApi)
}
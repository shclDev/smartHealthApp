package com.shcl.smarthealth.di

import com.shcl.smarthealth.data.repository.DashBoardRepositoryImpl
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.domain.repository.DashBoardRepository
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
}
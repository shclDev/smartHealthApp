package com.shcl.smarthealth.di

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.usecase.ble.GetBloodPressureUseCase
import com.shcl.smarthealth.domain.usecase.ble.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.ble.ScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.DashBoardUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetNutritionAdviceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideDashBoardUseCases(dashBoardRepository: DashBoardRepository) = DashBoardUseCase(
        getNutritionAdviceUseCase = GetNutritionAdviceUseCase(repository = dashBoardRepository)

    )

    @Provides
    fun provideOmronDeviceUseCase(omronRepository: OmronRepository) = OmronDeviceUseCase(
        scanDeviceUseCase = ScanDeviceUseCase(repository = omronRepository),
        getBloodPressureUseCase = GetBloodPressureUseCase(repository = omronRepository)
    )
}
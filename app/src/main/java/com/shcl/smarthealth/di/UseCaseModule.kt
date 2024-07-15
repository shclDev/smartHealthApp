package com.shcl.smarthealth.di

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.usecase.omron.BodyCompositionUseCase
import com.shcl.smarthealth.domain.usecase.omron.SetBloodPressureUseCase
import com.shcl.smarthealth.domain.usecase.omron.GetBloodPressureUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.RegisterDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.ScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.DashBoardUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetBloodPressureDBUseCase
import com.shcl.smarthealth.domain.usecase.dashboard.GetBodyCompositionDBUseCase
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
        getNutritionAdviceUseCase = GetNutritionAdviceUseCase(repository = dashBoardRepository),
        getBloodPressureDBUseCase = GetBloodPressureDBUseCase(repository = dashBoardRepository),
        getWeightDBUseCase = GetBodyCompositionDBUseCase(repository = dashBoardRepository)
    )

    @Provides
    fun provideOmronDeviceUseCase(omronRepository: OmronRepository) = OmronDeviceUseCase(
        scanDeviceUseCase = ScanDeviceUseCase(repository = omronRepository),
        getBloodPressureUseCase = GetBloodPressureUseCase(repository = omronRepository),
        setBloodPressureUseCase = SetBloodPressureUseCase(repository = omronRepository),
        registerDeviceUseCase = RegisterDeviceUseCase(repository = omronRepository),
        bodyCompositionUseCase = BodyCompositionUseCase(repository = omronRepository)
    )
    
}
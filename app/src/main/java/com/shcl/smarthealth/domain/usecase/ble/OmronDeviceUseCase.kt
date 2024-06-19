package com.shcl.smarthealth.domain.usecase.ble

import com.shcl.smarthealth.domain.usecase.dashboard.GetNutritionAdviceUseCase

data class OmronDeviceUseCase (
    val getBloodPressureUseCase : GetBloodPressureUseCase,
    val scanDeviceUseCase: ScanDeviceUseCase
)
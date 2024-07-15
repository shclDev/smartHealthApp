package com.shcl.smarthealth.domain.usecase.omron

data class OmronDeviceUseCase (
    val getBloodPressureUseCase : GetBloodPressureUseCase,
    val scanDeviceUseCase: ScanDeviceUseCase,
    val setBloodPressureUseCase: SetBloodPressureUseCase,
    val registerDeviceUseCase: RegisterDeviceUseCase,
    val bodyCompositionUseCase: BodyCompositionUseCase
)
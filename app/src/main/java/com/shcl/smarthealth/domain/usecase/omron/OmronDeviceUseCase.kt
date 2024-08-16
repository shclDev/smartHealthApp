package com.shcl.smarthealth.domain.usecase.omron

data class OmronDeviceUseCase (
    val getDataTransferUseCase : GetDataTransferUseCase,
    val scanDeviceUseCase: ScanDeviceUseCase,
    val setBloodPressureUseCase: SetBloodPressureUseCase,
    val registerDeviceUseCase: RegisterDeviceUseCase,
    val bodyCompositionUseCase: BodyCompositionUseCase,
    val getDeviceUseCase: GetDeviceUseCase
)
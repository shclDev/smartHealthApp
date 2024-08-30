package com.shcl.smarthealth.domain.usecase.isens


data class IsensDeviceUseCase (
    val isensScanDeviceUseCase: IsensScanDeviceUseCase,
    val getGlucoseRecordUseCase: GetGlucoseRecordUseCase,
    val setGlucoseRecordUserCase: SetGlucoseRecordUserCase,
    val getIsensDeviceUseCase : GetISensDeviceUseCase,
    val destoryDeviceUseCase : DestoryDeviceUseCase
)
package com.shcl.smarthealth.domain.usecase.measurement

data class MeasurementUseCase(
    val updateHeightUseCase: UpdateHeightUseCase,
    val updateBloodGlucoseUseCase: UpdateBloodGlucoseUseCase,
    val updateBloodPressureUseCase: UpdateBloodPressureUseCase,
    val updateBodyCompostionUseCase: UpdateBodyCompostionUseCase
)


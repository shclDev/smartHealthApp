package com.shcl.smarthealth.domain.usecase.dashboard

data class DashBoardUseCase (
    val getNutritionAdviceUseCase : GetNutritionAdviceUseCase,
    val getBloodPressureDBUseCase: GetBloodPressureDBUseCase,
    val getWeightDBUseCase: GetBodyCompositionDBUseCase,
    val getGlucoseDBUseCase: GetGlucoseDBUseCase,
    val getWeatherUseCase: GetWeatherUseCase
)
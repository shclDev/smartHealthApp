package com.shcl.smarthealth.domain.usecase.dashboard

import com.shcl.smarthealth.domain.usecase.user.LastedLoginUserRoomUpdateUseCase

data class DashBoardUseCase (
    val getNutritionAdviceUseCase : GetNutritionAdviceUseCase,
    val getBloodPressureDBUseCase: GetBloodPressureDBUseCase,
    val getWeightDBUseCase: GetBodyCompositionDBUseCase,
    val getGlucoseDBUseCase: GetGlucoseDBUseCase,
    val getWeatherUseCase: GetWeatherUseCase,
    val userInfoDBUseCase: UserInfoDBUseCase
)
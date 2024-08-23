package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DashBoardRepository{
    fun getNutritionAdvice() : Flow<String>
    fun getLastedBloodPressure()  : Flow<BloodPressureRoom>
    fun getLastedBodyComposition() : Flow<BodyCompositionRoom>
    fun getLastedGlucose() : Flow<GlucoseRecordRoom>
    suspend fun getCurrentWeather() : Flow<WeatherResponse?>
    fun getLastedLoginUser() : Flow<LastedLoginUserRoom>
    suspend fun getAllData() : Flow<OverallResponse?>
}

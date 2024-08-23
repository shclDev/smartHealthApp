package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DashBoardRemoteDataSource {
    fun getNutrtionAdvice() : Flow<String>
    fun getExerciseAdvice() : Flow<String>
    suspend fun getWeather() : Flow<WeatherResponse?>
    suspend fun getAllData() : Flow<OverallResponse?>
}
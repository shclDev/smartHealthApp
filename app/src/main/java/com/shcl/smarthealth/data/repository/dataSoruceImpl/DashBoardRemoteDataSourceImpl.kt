package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.api.WeatherApi
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DashBoardRemoteDataSourceImpl(
    private val dashBoardApi : DashBoardApi,
    private val weatherApi: WeatherApi
) : DashBoardRemoteDataSource{
    override fun getNutrtionAdvice(): Flow<String> {

        val msg = "영양 챙겨요!"
        return flow {
            emit(msg)
        }
    }

    override fun getExerciseAdvice(): Flow<String> {
        val msg = "운동 챙겨요!"
        return flow {
            emit(msg)
        }
    }

    override suspend fun getWeather(): Flow<Response<WeatherResponse>> {

        /**
         *  https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=6a52ad393f34aad788d110071d4cd7ac&lang=kr
         */
        val response = weatherApi.currentWeather("Seoul" , GlobalVariables.weatherApiKey , "kr" )

        return flow{
            emit(response)
        }
    }

}
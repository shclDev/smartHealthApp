package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.api.WeatherApi
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DashBoardRemoteDataSourceImpl(
    @NetworkModule.shcl private val dashBoardApi : DashBoardApi,
    @NetworkModule.weather private val weatherApi: WeatherApi
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

    override suspend fun getWeather(): Flow<WeatherResponse?> {

        /**
         *  https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=6a52ad393f34aad788d110071d4cd7ac&lang=kr
         */
        try{
            val response = weatherApi.currentWeather("Seoul" , GlobalVariables.weatherApiKey , "kr" )

            Log.d("weather" , "weather call...")
            if(response.isSuccessful){
                return flow{
                    emit(response.body())
                }
            }else{
                return flow{
                    emit(null)
                }
            }
        }catch (e : Exception){
            return flow{
                emit(null)
            }
        }
    }

    override suspend fun getAllData(): Flow<OverallResponse?> {
        try{
            val response = dashBoardApi.getDashboardAllData()

            if(response.success){
                return flow{
                    emit(response.data)
                }
            }
        }catch(e:Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(null)
        }
    }

}
package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    suspend fun currentWeather(
        @Query("q") city : String,
        @Query("appid") apiKey : String,
        @Query("lang") lang : String
    ) : Response<WeatherResponse>
}
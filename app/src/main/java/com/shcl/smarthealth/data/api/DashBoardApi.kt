package com.shcl.smarthealth.data.api

import retrofit2.http.GET

interface DashBoardApi {

    @GET("/dashboard/nutrition")
    suspend fun getNutritionAdvice() : Result<String>
    @GET("/dashboard/exercise")
    suspend fun getExerciseAdvice() : Result<String>
}
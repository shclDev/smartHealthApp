package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MeasurementApi {


    @POST("/personal/health/data/bodyComposition")
    suspend fun updateBodyComposition(
        @Body request : BodyCompositionRequest
    ) : Response<ApiResponse<String?>>


    @POST("/personal/health/data/bloodPressure")
    suspend fun updateBloodPressure(
        @Body request: BloodPressureRequest
    ) : Response<ApiResponse<String?>>

    @POST("/personal/health/data/bloodGlucose")
    suspend fun updateBloodGlucose(
        @Body request: BloodGlucoseRequest
    ) : Response<ApiResponse<String?>>

    @POST("/personal/health/data/height")
    suspend fun updateHeight(
        @Body request: HeightRequest
    ) : Response<ApiResponse<String?>>


}
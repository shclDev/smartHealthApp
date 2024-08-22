package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import kotlinx.coroutines.flow.Flow

interface MeasurmentRepository {

    suspend fun updateBloodPressure(request : BloodPressureRequest) : Flow<ApiResponse<String?>>
    suspend fun updateBloodGlucose(request : BloodGlucoseRequest) : Flow<ApiResponse<String?>>
    suspend fun updateBodyCompostion(request : BodyCompositionRequest) : Flow<ApiResponse<String?>>
    suspend fun updateHeight(requset : HeightRequest) : Flow<ApiResponse<String?>>

}
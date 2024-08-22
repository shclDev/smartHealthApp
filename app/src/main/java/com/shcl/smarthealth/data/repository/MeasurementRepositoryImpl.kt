package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import com.shcl.smarthealth.domain.repository.MeasurmentRepository
import kotlinx.coroutines.flow.Flow

class MeasurementRepositoryImpl(
    private val measureRecordRemoteDataSource: MeasureRecordRemoteDataSource
) : MeasurmentRepository{
    override suspend fun updateBloodPressure(request: BloodPressureRequest): Flow<ApiResponse<String?>> {
        return measureRecordRemoteDataSource.updateBloodPressureToServer(request)
    }

    override suspend fun updateBloodGlucose(request: BloodGlucoseRequest): Flow<ApiResponse<String?>> {
        return measureRecordRemoteDataSource.updateBloodGlucoseToServer(request)
    }

    override suspend fun updateBodyCompostion(request: BodyCompositionRequest): Flow<ApiResponse<String?>> {
        return measureRecordRemoteDataSource.updateBodyCompositionToServer(request)
    }

    override suspend fun updateHeight(requset: HeightRequest): Flow<ApiResponse<String?>> {
        return measureRecordRemoteDataSource.updateHeight(requset)
    }

}
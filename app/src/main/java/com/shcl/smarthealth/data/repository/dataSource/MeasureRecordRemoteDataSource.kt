package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import kotlinx.coroutines.flow.Flow

interface MeasureRecordRemoteDataSource {
    suspend fun updateBloodPressureToServer(bloodPressureRequest: BloodPressureRequest) : Flow<ApiResponse<String?>>
    suspend fun updateBloodGlucoseToServer(bloodGlucoseRequest: BloodGlucoseRequest) : Flow<ApiResponse<String?>>
    suspend fun updateBodyCompositionToServer(bodyCompositionRequest: BodyCompositionRequest) : Flow<ApiResponse<String?>>
    suspend fun updateHeight(heightRequest: HeightRequest) : Flow<ApiResponse<String?>>
}
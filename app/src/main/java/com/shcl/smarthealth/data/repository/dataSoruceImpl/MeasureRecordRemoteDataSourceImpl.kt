package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import androidx.datastore.preferences.protobuf.Api
import com.shcl.smarthealth.data.api.MeasurementApi
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordRemoteDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeasureRecordRemoteDataSourceImpl @Inject constructor(private val api: MeasurementApi) : MeasureRecordRemoteDataSource {

    override suspend fun updateBloodPressureToServer(bloodPressureRequest: BloodPressureRequest) : Flow<ApiResponse<String?>> {

        try{
            val response = api.updateBloodPressure(bloodPressureRequest)

            if(response.isSuccessful){
                response.body()?.let {
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                            success = false,
                            code = response.code().toString(),
                            message = response.message(),
                            data = null
                        )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                    success = false,
                    code = "",
                    message = "",
                    data = null
                )
            )
        }
    }

    override suspend fun updateBloodGlucoseToServer(bloodGlucoseRequest: BloodGlucoseRequest) : Flow<ApiResponse<String?>>{
        try{
            val response = api.updateBloodGlucose(bloodGlucoseRequest)

            if(response.isSuccessful){
                response.body()?.let {
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                            success = false,
                            code = response.code().toString(),
                            message = response.message(),
                            data = null
                        )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                    success = false,
                    code = "",
                    message = "",
                    data = null
                )
            )
        }
    }

    override suspend fun updateBodyCompositionToServer(bodyCompositionRequest: BodyCompositionRequest) : Flow<ApiResponse<String?>> {
        try{
            val response = api.updateBodyComposition(bodyCompositionRequest)

            if(response.isSuccessful){
                response.body()?.let {
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                            success = false,
                            code = response.code().toString(),
                            message = response.message(),
                            data = null
                        )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                    success = false,
                    code = "",
                    message = "",
                    data = null
                )
            )
        }
    }

    override suspend fun updateHeight(heightRequest: HeightRequest) : Flow<ApiResponse<String?>>{
        try{
            val response = api.updateHeight(heightRequest)

            if(response.isSuccessful){
                response.body()?.let {
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                            success = false,
                            code = response.code().toString(),
                            message = response.message(),
                            data = null
                        )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                    success = false,
                    code = "",
                    message = "",
                    data = null
                )
            )
        }
    }
}
package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import androidx.datastore.preferences.protobuf.Api
import com.shcl.smarthealth.data.api.SurveyApi
import com.shcl.smarthealth.data.repository.dataSource.SurveyRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.survey.CategoryQuestionResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyInfoResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyStartRequest
import com.shcl.smarthealth.domain.model.remote.survey.SurveyStartResponse
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SurveyRemoteDataSourceImpl(
    @NetworkModule.shcl private val surveyApi: SurveyApi
) : SurveyRemoteDataSource{
    override suspend fun surveyInfo(): Flow<ApiResponse<SurveyInfoResponse>> {
        try{
            val response = surveyApi.surveyInfo()

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


        }catch(e : Exception){
          Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(ApiResponse(
                success = false,
                code = "",
                message = "",
                data = null
            ))
        }
    }

    override suspend fun surveyStart(surveyId: Int): Flow<ApiResponse<SurveyStartResponse>> {
        try{
            val response = surveyApi.surveyStart(SurveyStartRequest(surveyId))

            if(response.isSuccessful){
                response.body()?.let {
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(ApiResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    ))
                }
            }
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(success = false, code = "", message = "",data = null)
            )
        }


    }

    override suspend fun surveyComplete(answerId: Int): Flow<ApiResponse<String?>> {
       val response = surveyApi.surveyComplete(answerId)

        try{
            if(response.isSuccessful){
                response.body()?.let{
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(ApiResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    ))
                }
            }
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }


        return flow{
            emit(ApiResponse(
                success = false,
                code = response.code().toString(),
                message = response.message(),
                data = null
            ))
        }

    }

    override suspend fun surveyCategoryQuestion(
        surveyId: Int,
        category: String
    ): Flow<CategoryQuestionResponse> {
        val response = surveyApi.surveyCategoryQuestion(surveyId , category)

        try{
            if(response.isSuccessful){
                response.body()?.let{
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(CategoryQuestionResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    ))
                }
            }
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }


        return flow{
            emit(CategoryQuestionResponse(
                success = false,
                code = response.code().toString(),
                message = response.message(),
                data = null
            ))
        }
    }

    override suspend fun surveyCategoryAnswer(request: CategoryQuestionRequest): Flow<ApiResponse<String?>> {

        val response = surveyApi.surveyCategoryAnswer(request)

        try{
            if(response.isSuccessful){
                response.body()?.let{
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(ApiResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    ))
                }
            }
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }


        return flow{
            emit(ApiResponse(
                success = false,
                code = response.code().toString(),
                message = response.message(),
                data = null
            ))
        }

    }

}
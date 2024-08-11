package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.survey.CategoryQuestionResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyInfoResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyStartResponse
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SurveyApi {


    ///설문조사 정보 조회 ( 버전 관리 위해)
    @GET("/personal/health/survey/latest")
    suspend fun surveyInfo(
    ) : Response<ApiResponse<SurveyInfoResponse>>


    ///설문조사 답변 시작
    @POST("/personal/health/survey/answer/start")
    suspend fun surveyStart(
    ) : Response<ApiResponse<SurveyStartResponse>>

    ///설문조사 답변 완료
    @POST("/personal/health/survey/answer/complete")
    suspend fun surveyComplete(
        answerId : Int
    ) : Response<ApiResponse<String?>>

    /// 카테고리별 설문조사 문항 조회 (문항 받아올때)
    @GET("/personal/health/survey/{surveyId}/questions")
    suspend fun surveyCategoryQuestion(
        @Path(value = "surveyId") surveyId : Int = 1,
        @Query("category") category : String
    ) : Response<CategoryQuestionResponse>

    /// 카테고리별 설문조사 답변 데이터 저장
    @POST("/personal/health/survey/answer/category")
    suspend fun surveyCategoryAnswer(
        @Body request: CategoryQuestionRequest
    ) : Response<ApiResponse<String?>>



}
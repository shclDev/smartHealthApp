package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.survey.CategoryQuestionResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyInfoResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyStartResponse
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import kotlinx.coroutines.flow.Flow

interface SurveyRemoteDataSource {
    suspend fun surveyInfo() : Flow<ApiResponse<SurveyInfoResponse>>

    suspend fun surveyStart() : Flow<ApiResponse<SurveyStartResponse>>

    suspend fun surveyComplete(answerId : Int) : Flow<ApiResponse<String?>>

    suspend fun surveyCategoryQuestion(surveyId: Int , category : String) : Flow<CategoryQuestionResponse>

    suspend fun surveyCategoryAnswer(request : CategoryQuestionRequest) : Flow<ApiResponse<String?>>


}
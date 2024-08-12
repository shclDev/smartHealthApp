package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.SurveyRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.survey.CategoryQuestionResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyInfoResponse
import com.shcl.smarthealth.domain.model.remote.survey.SurveyStartResponse
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import com.shcl.smarthealth.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow

class SurveyRepositoryImpl(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
) : SurveyRepository{
    override suspend fun surveyInfo(): Flow<ApiResponse<SurveyInfoResponse>> {
        return surveyRemoteDataSource.surveyInfo()
    }

    override suspend fun surveyStart(surveyId: Int): Flow<ApiResponse<SurveyStartResponse>> {
       return surveyRemoteDataSource.surveyStart(surveyId)
    }

    override suspend fun surveyComplete(answerId: Int): Flow<ApiResponse<String?>> {
       return surveyRemoteDataSource.surveyComplete(answerId)
    }

    override suspend fun surveyCategoryQuestion(
        surveyId: Int,
        category: String
    ): Flow<CategoryQuestionResponse> {
        return surveyRemoteDataSource.surveyCategoryQuestion(surveyId, category)
    }

    override suspend fun surveyCategoryAnswer(request: CategoryQuestionRequest): Flow<ApiResponse<String?>> {
        return surveyRemoteDataSource.surveyCategoryAnswer(request)
    }

}
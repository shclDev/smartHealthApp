package com.shcl.smarthealth.domain.usecase.survey

import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import com.shcl.smarthealth.domain.repository.SurveyRepository
import javax.inject.Inject

class SetCategoryAnswerUseCase  @Inject constructor(private val repository: SurveyRepository) {

    suspend operator fun invoke(request: CategoryQuestionRequest) = repository.surveyCategoryAnswer(request)
}
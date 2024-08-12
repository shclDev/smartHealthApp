package com.shcl.smarthealth.domain.usecase.survey

import com.shcl.smarthealth.domain.repository.SurveyRepository
import javax.inject.Inject

class StartSurveyUseCase  @Inject constructor(private val repository: SurveyRepository) {
    suspend operator fun invoke(surveyId : Int)  = repository.surveyStart(surveyId)
}
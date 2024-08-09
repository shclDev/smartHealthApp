package com.shcl.smarthealth.domain.usecase.survey

import com.shcl.smarthealth.domain.repository.SurveyRepository
import javax.inject.Inject

class GetCategoryQuestionUseCase @Inject constructor(private val repository: SurveyRepository) {

    suspend operator fun invoke(surveyId : Int =1 , category : String) = repository.surveyCategoryQuestion(surveyId, category)

}
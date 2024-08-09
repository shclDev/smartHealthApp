package com.shcl.smarthealth.domain.usecase.survey

data class SurveyUseCase(
    val startSurveyUseCase: StartSurveyUseCase,
    val completeSurveyUseCase: CompleteSurveyUseCase,
    val setCategoryAnswerUseCase: SetCategoryAnswerUseCase,
    val getCategoryQuestionUseCase: GetCategoryQuestionUseCase,
    val getSurveysInfoUseCase: GetSurveyInfoUseCase
)

package com.shcl.smarthealth.domain.usecase.survey

import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase

data class SurveyUseCase(
    val startSurveyUseCase: StartSurveyUseCase,
    val completeSurveyUseCase: CompleteSurveyUseCase,
    val setCategoryAnswerUseCase: SetCategoryAnswerUseCase,
    val getCategoryQuestionUseCase: GetCategoryQuestionUseCase,
    val getSurveysInfoUseCase: GetSurveyInfoUseCase,
    val voiceUseCase: VoiceUseCase
)

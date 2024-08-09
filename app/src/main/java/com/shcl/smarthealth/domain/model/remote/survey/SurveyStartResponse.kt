package com.shcl.smarthealth.domain.model.remote.survey

data class SurveyStartResponse(
    val answerDate: String,
    val answerStatus: String,
    val answerStatusName: String,
    val createdDate: String,
    val id: Int,
    val lastCompletedCategory: String,
    val lastCompletedCategoryName: String,
    val lastModifiedDate: String,
    val surveyId: Int,
    val userId: Int
)
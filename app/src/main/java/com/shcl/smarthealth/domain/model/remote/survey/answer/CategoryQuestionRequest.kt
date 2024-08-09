package com.shcl.smarthealth.domain.model.remote.survey.answer

data class CategoryQuestionRequest(
    val answerId : Int,
    val category : String,
    val answers : List<Answer>
)

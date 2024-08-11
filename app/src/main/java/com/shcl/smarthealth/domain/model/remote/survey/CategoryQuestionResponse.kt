package com.shcl.smarthealth.domain.model.remote.survey

data class CategoryQuestionResponse(
    val success : Boolean,
    val code : String,
    val message : String,
    var data: List<Question>?
)
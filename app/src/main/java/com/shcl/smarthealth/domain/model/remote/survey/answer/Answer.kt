package com.shcl.smarthealth.domain.model.remote.survey.answer

data class Answer(
    val questionId : Int,
    val answerType : String,
    val answer : Any
)

package com.shcl.smarthealth.domain.model.remote.survey.answer

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("questionId") val questionId : Int,
    @SerializedName("answerType") val answerType : String,
    @SerializedName("answer") var answer : Any?
)

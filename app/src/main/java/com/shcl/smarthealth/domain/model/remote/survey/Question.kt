package com.shcl.smarthealth.domain.model.remote.survey

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("answerType") val answerType: String,
    @SerializedName("answerTypeName") val answerTypeName: String,
    @SerializedName("category") val category: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("mainSeq") val mainSeq: Int,
    @SerializedName("questionId") val questionId: Int,
    @SerializedName("seq") val seq: String,
    @SerializedName("subSeq") val subSeq: Int,
    @SerializedName("surveyId") val surveyId: Int
)
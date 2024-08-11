package com.shcl.smarthealth.domain.model.remote.survey.answer

import com.google.gson.annotations.SerializedName

data class CategoryQuestionRequest(
    @SerializedName("answerId") val answerId : Int,
    @SerializedName("category") val category : String,
    @SerializedName("answers") val answers : List<Answer>
)

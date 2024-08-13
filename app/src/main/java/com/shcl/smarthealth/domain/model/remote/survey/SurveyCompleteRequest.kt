package com.shcl.smarthealth.domain.model.remote.survey
import com.google.gson.annotations.SerializedName

data class SurveyCompleteRequest(
    @SerializedName("answerId") val answerId : Int,
)
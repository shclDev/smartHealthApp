package com.shcl.smarthealth.domain.model.remote.survey

import com.google.gson.annotations.SerializedName

data class SurveyStartRequest(
    @SerializedName("surveyId") val surveyId : Int,
)
package com.shcl.smarthealth.domain.model.remote.survey

data class SurveyInfoResponse(
    val id : Int,
    val title : String,
    val version : String,
    val enabled : Boolean
)

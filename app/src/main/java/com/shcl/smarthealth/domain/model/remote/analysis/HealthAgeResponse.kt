package com.shcl.smarthealth.domain.model.remote.analysis

import com.google.gson.annotations.SerializedName

data class HealthAgeResponse (
    @SerializedName("realAge") val realAge : Int,
    @SerializedName("healthAge") val healthAge : Int
)

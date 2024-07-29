package com.shcl.smarthealth.domain.model.remote.user

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @SerializedName("type") val type : String,
    @SerializedName("token") val token : String,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("authCode") val authCode : String
)
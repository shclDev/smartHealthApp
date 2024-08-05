package com.shcl.smarthealth.domain.model.remote.user

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("type") var type : String,
    @SerializedName("token") var token : String,
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("authCode") var authCode : String
)
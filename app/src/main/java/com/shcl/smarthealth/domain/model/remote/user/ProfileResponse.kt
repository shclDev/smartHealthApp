package com.shcl.smarthealth.domain.model.remote.user

import com.google.gson.annotations.SerializedName


data class ProfileResponse (
    @SerializedName("name") val name : String,
    @SerializedName("nickName") val nickName : String,
    @SerializedName("birthDate") val birthDate : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("mobile") val mobile : String,
    @SerializedName("auth") val auth : String,
    @SerializedName("lastLoginDate") val lastLoginDate : String
)
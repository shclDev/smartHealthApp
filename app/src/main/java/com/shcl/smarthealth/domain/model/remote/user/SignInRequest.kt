package com.shcl.smarthealth.domain.model.remote.user

data class SignInRequest(
    var mobile : String,
    var birthDate : String? = "",
)
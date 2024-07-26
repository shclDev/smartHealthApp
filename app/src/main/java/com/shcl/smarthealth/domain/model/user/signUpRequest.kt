package com.shcl.smarthealth.domain.model.user

data class signUpRequest(
    var name : String,
    var nickName : String,
    var birthDate : String,
    var gender : String,
    var mobile : String
)
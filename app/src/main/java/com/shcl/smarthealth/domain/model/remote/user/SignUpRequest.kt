package com.shcl.smarthealth.domain.model.remote.user

import android.net.Uri

data class SignUpRequest(
    var name : String,
    var nickName : String? = "",
    var birthDate : String,
    var gender : String,
    var mobile : String,
    var picture : Uri
)
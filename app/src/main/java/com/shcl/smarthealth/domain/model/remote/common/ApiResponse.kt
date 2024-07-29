package com.shcl.smarthealth.domain.model.remote.common

data class ApiResponse<Any>(
    val success : Boolean,
    val code : String,
    val message : String,
    val data : Any?
)

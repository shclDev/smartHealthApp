package com.shcl.smarthealth.domain.model.remote.dashboard

data class WeightInfo(
    val beforeValue: Float? = 0.0f,
    val changeInValue: Float? = 0.0f,
    val currentValue: Float? = 0.0f,
    val dataUnitName: String,
    val id: Int,
    val measureDate: String,
    val measureType: String,
    val measureTypeName: String
)
package com.shcl.smarthealth.domain.model.remote.dashboard

data class HeartRateInfo(
    val beforeValue: Int? = 0,
    val changeInValue: Int? = 0,
    val currentValue: Int? = 0,
    val dataUnitName: String,
    val id: Int,
    val measureDate: String,
    val measureType: String,
    val measureTypeName: String
)
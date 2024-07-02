package com.shcl.smarthealth.domain.model.omron

data class BloodPressure(
    val diastolic : Float,
    val diastolicUnit : String,
   // val meanArterial : Float,
    val timeStamp : String,
    val pulseRate : Float,
    val systolic : Float,
    val systolicUnit : String

)

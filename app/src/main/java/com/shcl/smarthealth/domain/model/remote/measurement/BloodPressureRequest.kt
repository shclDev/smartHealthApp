package com.shcl.smarthealth.domain.model.remote.measurement

data class BloodPressureRequest(
    var diastolic : Float,
    var diastolicUnit : String,
    var systolic : Float,
    var systolicUnit : String,
    var pulseRate: Float,
    var timeStamp : String
)

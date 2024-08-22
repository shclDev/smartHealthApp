package com.shcl.smarthealth.domain.model.remote.measurement

data class BodyCompositionRequest(
    var userIndex : Int,
    var sequenceNumber : Int,
    var weight:Float,
    var weightUnit: String,
    var bodyAge: Int,
    var bmi:Float,
    var musclePercentage: Float,
    var bodyFatPercentage: Float,
    var skeletalMusclePercentage: Float,
    var timeStamp: String
)

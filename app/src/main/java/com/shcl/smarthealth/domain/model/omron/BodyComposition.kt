package com.shcl.smarthealth.domain.model.omron

data class BodyComposition(
    val userIndex : Int,
    val sequenceNumber : Int,
    val weight : Float,
    val weightUnit : String,
    val bodyAge : Int,
    val bmi : Float,
    val musclePercentage : Float,
    val bodyFatPercentage : Float,
    val skeletalMusclePercentage : Float,
    val timeStamp : String
)

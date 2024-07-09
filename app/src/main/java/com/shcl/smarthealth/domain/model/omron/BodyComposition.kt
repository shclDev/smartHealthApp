package com.shcl.smarthealth.domain.model.omron

data class BodyComposition(
    val userIndex : Int,
    val sequenceNumber : Int,
    val weight : Long,
    val weightUnit : String,
    val fatPercentage : Float,
    val timeStamp : String
)

package com.shcl.smarthealth.domain.model.remote.measurement

data class BloodGlucoseRequest(
    var glucoseData : Double,
    var flagCs : Int,
    var flagHilow : Int,
    var flagContext : Int,
    var flagMeal : Int,
    var flagFasting : Int,
    var flagKetone : Int,
    var flagNomark : Int,
    var timeoffset : Int,
    var time : Long
)

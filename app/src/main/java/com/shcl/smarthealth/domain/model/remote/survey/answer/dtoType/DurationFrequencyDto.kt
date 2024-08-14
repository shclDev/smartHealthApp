package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DurationType

data class DurationFrequencyDto(
    var durationType: String,
    var value : Int
)
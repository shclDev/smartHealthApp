package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import java.time.LocalTime

data class StartEndTimeDto(
    val startTime : LocalTime,
    val endTime : LocalTime
)
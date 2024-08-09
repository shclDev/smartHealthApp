package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType

data class DiseaseHistoryDto(
    val diseaseType: DiseaseType,
    val yearOfDiagnosis : Int,
    val medication : Boolean,
    val durationOfMedication : Int
)

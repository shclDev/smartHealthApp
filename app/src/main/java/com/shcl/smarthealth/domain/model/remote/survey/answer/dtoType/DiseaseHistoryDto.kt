package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType

data class DiseaseHistoryDto(
    val diseaseType: String,
    var yearOfDiagnosis : Int,
    var medication : Boolean,
    var durationOfMedication : Int
)

package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType

data class CancerHistoryDto(
    val diseaseType : DiseaseType,
    val yearOfDiagnosis : Int,
    val operation : Boolean,
    val chemoTherapy : Boolean,
    val hormoneTherapy : Boolean,
    val radiationTherapy : Boolean
)
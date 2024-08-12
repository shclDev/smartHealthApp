package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType

data class CancerHistoryDto(
    val diseaseType : String,
    var yearOfDiagnosis : Int,
    var operation : Boolean,
    var chemoTherapy : Boolean,
    var hormoneTherapy : Boolean,
    var radiationTherapy : Boolean
)
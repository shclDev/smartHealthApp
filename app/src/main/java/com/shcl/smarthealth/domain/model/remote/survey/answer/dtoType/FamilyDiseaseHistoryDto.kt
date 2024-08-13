package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyMemberType

data class FamilyDiseaseHistoryDto (
    var diseaseType : String,
    var family : List<String>
)
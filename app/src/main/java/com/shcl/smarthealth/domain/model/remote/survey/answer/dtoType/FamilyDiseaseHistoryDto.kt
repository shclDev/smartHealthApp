package com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyMemberType

data class FamilyDiseaseHistoryDto (
    val diseaseType : DiseaseType,
    val family : List<FamilyMemberType>
)
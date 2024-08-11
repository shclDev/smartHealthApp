package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

interface SurveyEnumType {
    fun getValue(value : Any) : Any
    fun getKorName(surveyByLevel: SurveyByLevel) : String
}
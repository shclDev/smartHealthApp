package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyDiseaseType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class DurationType(
    val korName : String,
    val value : Any,
) : SurveyEnumType{

    MONTHLY("월간","MONTHLY"){
        override fun getValue(value: Any): Any {
           return "MONTHLY"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "한달에"
            }
        }


    },


    WEEKLY("주간" , "WEEKLY"){
        override fun getValue(value: Any): Any {
            return "WEEKLY"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "한 주에"
            }
        }

    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
            answerMap.clear()

            enumValues<DurationType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }

}

package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.GoodBadType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class FrequencyType( val korName : String,
                          val value : Any) : SurveyEnumType{
    ALWAYS("항상","ALWAYS"){
        override fun getValue(value: Any): Any {
            return "ALWAYS"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5-> return "항상 그런 편이다"
            }
        }
    },

    NORMAL("보통","NORMAL"){
        override fun getValue(value: Any): Any {
            return "NORMAL"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "보통이다"
            }
        }
    },

    NO("아님","NO"){
        override fun getValue(value: Any): Any {
            return "NO"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "아닌편이다"
            }
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{

            answerMap.clear()

            enumValues<FrequencyType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }
}

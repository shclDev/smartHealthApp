package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DiseaseType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class DaysFrequencyType(
    val korName : String,
    val value : Any,
) : SurveyEnumType{

    NEVER("전혀 그렇지 않다" , "NEVER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                return "전혀 그렇지 않다"
            }
        }

        override fun getValue(value: Any): Any {
            return "NEVER"
        }
    },

    DAYS("수일 정도" , "DAYS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "수일 정도"
            }
        }

        override fun getValue(value: Any): Any {
            return "DAYS"
        }
    },

    WEEK("1주일 이상" , "WEEK"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "1주일 이상"
            }
        }

        override fun getValue(value: Any): Any {
            return "WEEK"
        }
    },

    EVERYDAY("거의 매일" , "EVERYDAY"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            return when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "거의 매일"
            }
        }

        override fun getValue(value: Any): Any {
            return "EVERYDAY"
        }
    },;

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
            answerMap.clear()

            enumValues<DaysFrequencyType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }

}

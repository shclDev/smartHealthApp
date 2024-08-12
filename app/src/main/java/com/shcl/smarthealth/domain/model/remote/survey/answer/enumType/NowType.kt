package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.CancerType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class NowType (  val korName : String,
                      val value : Any): SurveyEnumType{

    NEVER("없었음","NEVER"){

        override fun getValue(value: Any): Any {
            return "NEVER"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5-> return "없었음"
                SurveyByLevel.LEVEL4 -> return "과거에는 피웠으나 현재 피우지 않는다."

            }
        }
    },

    NOT_NOW("현재는 아님","NOT_NOW"){
        override fun getValue(value: Any): Any {
            return "NOT_NOW"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5-> return "없었음"
                SurveyByLevel.LEVEL4 -> return "전혀 피운 적이 없다(평생 10개 미만)"

            }
        }
    },

    NOW("현재","NOW"){
        override fun getValue(value: Any): Any {
            return "NOW"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5-> return "없었음"
                SurveyByLevel.LEVEL4 -> return "현재도 피우고 있다"

            }
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{

           answerMap.clear()

            enumValues<NowType>().map {
                answerMap.put(it.getKorName(surveyByLevel = surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }
}
package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class GoodBadType(
    val korName : String,
    val value : Any,
) : SurveyEnumType {

    VERY_GOOD("매우 좋음","VERY_GOOD"){
        override fun getValue(value: Any): Any {
            return "VERY_GOOD"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5,
                SurveyByLevel.LEVEL4 -> return "매우 좋음"
                SurveyByLevel.LEVEL2 -> return "매우 좋았다"
            }
        }

    },

    GOOD("좋음","GOOD"){


        override fun getValue(value: Any): Any {
            return "GOOD"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5,
                SurveyByLevel.LEVEL4 -> return "상당히 좋음"
                SurveyByLevel.LEVEL2 -> return "상당히 좋았다"
            }
        }
    },

    BAD("나쁨","BAD"){
        override fun getValue(value: Any): Any {
            return "BAD"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5,
                SurveyByLevel.LEVEL4 -> return "상당히 나빳다"
                SurveyByLevel.LEVEL2 -> return "상당히 나빳다"
            }
        }
    },

    VERY_BAD("매우 나쁨","VERY_BAD"){
        override fun getValue(value: Any): Any {
            return "VERY_BAD"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL5,
                SurveyByLevel.LEVEL4 -> return "매우 나빳다"
                SurveyByLevel.LEVEL2 -> return "매우 나빳다"
            }
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
            enumValues<GoodBadType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }
}
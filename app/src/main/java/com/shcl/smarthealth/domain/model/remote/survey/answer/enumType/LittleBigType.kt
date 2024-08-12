package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class LittleBigType(
    val korName : String,
    val value : Any,
): SurveyEnumType{
    NONE("없음","NONE"){
        override fun getValue(value: Any): Any {
            return "NONE"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5-> return "없음"
                SurveyByLevel.LEVEL3-> return "전혀 어렵지 않았다"
                SurveyByLevel.LEVEL2-> return "전혀 문제가 없었다"
            }
        }
    },

    LITTLE("약간","LITTLE"){
        override fun getValue(value: Any): Any {
            return "LITTLE"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5-> return "약간"
                SurveyByLevel.LEVEL3-> return "약간 어려웠다"
                SurveyByLevel.LEVEL2-> return "매우 적은 문제가 있었다"
            }
        }
    },

    SOME("얼마간","SOME"){
        override fun getValue(value: Any): Any {
            return "SOME"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when (surveyByLevel) {
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5 -> return "얼마간"
                SurveyByLevel.LEVEL3 -> return "많이 어려웠다"
                SurveyByLevel.LEVEL2 -> return "얼마간 문제가 있었다"
            }
        }
    },

        BIG("큼","BIG"){
        override fun getValue(value: Any): Any {
            return "BIG"
        }

            override fun getKorName(surveyByLevel: SurveyByLevel): String {
                when (surveyByLevel) {
                    SurveyByLevel.LEVEL1,
                    SurveyByLevel.LEVEL4,
                    SurveyByLevel.LEVEL5 -> return "큼"
                    SurveyByLevel.LEVEL3 -> return "매우 많이 어려웠다"
                    SurveyByLevel.LEVEL2 -> return "매우 큰 문제가 있었다"
                }
            }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
            answerMap.clear()
            enumValues<LittleBigType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }
}

package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.LittleBigType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class CancerType( val korName : String,
                       val value : Any,
) : SurveyEnumType {
    LUNG_CANCER("폐암" , "LUNG_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "폐암"
            }
        }

        override fun getValue(value: Any): Any {
            return "LUNG_CANCER"
        }
    },
    GASTRIC_CANCER("위암" , "GASTRIC_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,
                SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "위암"
            }
        }

        override fun getValue(value: Any): Any {
            return "GASTRIC_CANCER"
        }
    },

    COLON_CANCER("대장암" , "COLON_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "대장암"
            }
        }

        override fun getValue(value: Any): Any {
            return "COLON_CANCER"
        }
    },

    LIVER_CANCER("간암" , "LIVER_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "간암"
            }
        }

        override fun getValue(value: Any): Any {
            return "LIVER_CANCER"
        }
    },

    THYROID_CANCER("갑상선암" , "THYROID_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "갑상선암"
            }
        }

        override fun getValue(value: Any): Any {
            return "THYROID_CANCER"
        }
    },

    BREAST_CANCER("유방암" , "BREAST_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "유방암"
            }
        }

        override fun getValue(value: Any): Any {
            return "BREAST_CANCER"
        }
    },

    ETC_CANCER("기타암" , "ETC_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "기타암"
            }
        }

        override fun getValue(value: Any): Any {
            return "ETC_CANCER"
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
           answerMap.clear()


            enumValues<CancerType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

    }
}
package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FamilyMemberType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class FamilyDiseaseType(val korName : String,
                             val value : Any,
) : SurveyEnumType {


    NONE("해당 없음" , "NONE"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "해당 없음"
            }
        }

        override fun getValue(value: Any): Any {
            return "NONE"
        }
    },

    HIGH_BLOOD_PRESSURE("고혈압" , "HIGH_BLOOD_PRESSURE"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "고혈압"
            }
        }

        override fun getValue(value: Any): Any {
            return "HIGH_BLOOD_PRESSURE"
        }
    },

    DIABETES("당뇨" , "DIABETES"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "당뇨"
            }
        }

        override fun getValue(value: Any): Any {
            return "DIABETES"
        }
    },

    ANGINA_OR_MYOCARDIAL_INFARCTION("심근경색/협십증" , "ANGINA_OR_MYOCARDIAL_INFARCTION"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "심근경색/협십증"
            }
        }

        override fun getValue(value: Any): Any {
            return "ANGINA_OR_MYOCARDIAL_INFARCTION"
        }
    },


    STROKE("뇌졸증(중풍)" , "STROKE"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "뇌졸증(중풍)"
            }
        }

        override fun getValue(value: Any): Any {
            return "STROKE"
        }
    },

    DEMENTIA("치매" , "DEMENTIA"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "치매"
            }
        }

        override fun getValue(value: Any): Any {
            return "DEMENTIA"
        }
    },


    CHRONIC_HEPATITIS_OR_LIVER_CIRRHOSIS("만성간염/간경변" , "CHRONIC_HEPATITIS_OR_LIVER_CIRRHOSIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "만성간염/간경변"
            }
        }

        override fun getValue(value: Any): Any {
            return "CHRONIC_HEPATITIS_OR_LIVER_CIRRHOSIS"
        }
    },

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

    COLON_CANCER_OR_RECTAL_CANCER("대장,직장암" , "COLON_CANCER_OR_RECTAL_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "대장,직장암"
            }
        }

        override fun getValue(value: Any): Any {
            return "COLON_CANCER_OR_RECTAL_CANCER"
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

    PANCREATIC_CANCER("췌장암" , "PANCREATIC_CANCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1,SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "췌장암"
            }
        }

        override fun getValue(value: Any): Any {
            return "PANCREATIC_CANCER"
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
            enumValues<FamilyDiseaseType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

        fun getKorName(value : String) : String? {
            val findName = enumValues<FamilyDiseaseType>().find { it.value == value }
            if (findName != null) {
                return findName.korName
            }
            return null
        }

    }

}
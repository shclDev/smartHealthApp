package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DurationType.Companion
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class DiseaseType( val korName : String,
                        val value : Any,
) : SurveyEnumType {


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

    HYPERLIPIDEMIA ("고지혈증" , "HYPERLIPIDEMIA"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "고지혈증"
            }
        }

        override fun getValue(value: Any): Any {
            return "HYPERLIPIDEMIA"
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


    TUBERCULOSIS_OF_LUNG("폐결핵" , "TUBERCULOSIS_OF_LUNG"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "폐결핵"
            }
        }

        override fun getValue(value: Any): Any {
            return "TUBERCULOSIS_OF_LUNG"
        }
    }, HEPATITIS("B형,C형 간염보유자" , "HEPATITIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "B형,C형 간염보유자"
            }
        }

        override fun getValue(value: Any): Any {
            return "HEPATITIS"
        }
    }, OSTEOPOROSIS("골다골증" , "OSTEOPOROSIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "골다골증"
            }
        }

        override fun getValue(value: Any): Any {
            return "OSTEOPOROSIS"
        }
    },

    HYPERTHYROIDISM("갑상성기능항진증" , "HYPERTHYROIDISM"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "갑상성기능항진증"
            }
        }

        override fun getValue(value: Any): Any {
            return "HYPERTHYROIDISM"
        }
    },

    ASTHMA("천식" , "ASTHMA"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "천식"
            }
        }

        override fun getValue(value: Any): Any {
            return "ASTHMA"
        }
    }, ALLERGIC_RHINITIS("알레르기 비염" , "ALLERGIC_RHINITIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "알레르기 비염"
            }
        }

        override fun getValue(value: Any): Any {
            return "ALLERGIC_RHINITIS"
        }
    }, GASTRIC_ULCER_OR_DUODENAL_ULCER("위궤양/십이지장 궤양" , "GASTRIC_ULCER_OR_DUODENAL_ULCER"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "위궤양/십이지장 궤양"
            }
        }

        override fun getValue(value: Any): Any {
            return "GASTRIC_ULCER_OR_DUODENAL_ULCER"
        }
    },REFLUX_ESOPHAGITIS("역류성 식도염" , "REFLUX_ESOPHAGITIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "역류성 식도염"
            }
        }

        override fun getValue(value: Any): Any {
            return "REFLUX_ESOPHAGITIS"
        }
    },

    CHRONIC_KIDNEY_DISEASE("만성 신장 질환" , "CHRONIC_KIDNEY_DISEASE"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "만성 신장 질환"
            }
        }

        override fun getValue(value: Any): Any {
            return "CHRONIC_KIDNEY_DISEASE"
        }
    },

    BENIGN_PROSTATIC_HYPERPLASIA("전립선비대증" , "BENIGN_PROSTATIC_HYPERPLASIA"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "전립선비대증"
            }
        }

        override fun getValue(value: Any): Any {
            return "BENIGN_PROSTATIC_HYPERPLASIA"
        }
    },

    GLAUCOMA("녹내장" , "GLAUCOMA"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "녹내장"
            }
        }

        override fun getValue(value: Any): Any {
            return "GLAUCOMA"
        }
    },

    ARTHRITIS("관절염" , "ARTHRITIS"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "관절염"
            }
        }

        override fun getValue(value: Any): Any {
            return "ARTHRITIS"
        }
    },

    ETC_DISEASE("기타질환" , "ETC_DISEASE"){
        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "기타질환"
            }
        }

        override fun getValue(value: Any): Any {
            return "ETC_DISEASE"
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap(surveyByLevel: SurveyByLevel) : HashMap<String , Any>{
            answerMap.clear()

            enumValues<DiseaseType>().map {
                answerMap.put(it.getKorName(surveyByLevel) , it.value)
            }.forEach {  }

            return answerMap
        }

        fun getKorName(value : String) : String? {
            val findName = enumValues<DiseaseType>().find { it.value == value }
            if (findName != null) {
                return findName.korName
            }
            return null
        }

    }
}
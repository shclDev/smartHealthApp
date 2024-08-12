package com.shcl.smarthealth.domain.model.remote.survey.answer.enumType

import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel

enum class FamilyMemberType ( val korName : String,
                              val value : Any) : SurveyEnumType{

    FATHER("아버지" , "FATHER"){
        override fun getValue(value: Any): Any {
           return "FATHER"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "아버지"
            }
        }

    },

    MOTHER("어머니","MONTHER"){
        override fun getValue(value: Any): Any {
            return "MONTHER"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "어머니"
            }
        }
    },



    SIBLING("형제자매","SIBLING"){
        override fun getValue(value: Any): Any {
            return "SIBLING"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "형제자매"
            }
        }
    },

    CHILDREN("자녀","CHILDREN"){
        override fun getValue(value: Any): Any {
            return "CHILDREN"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "자녀"
            }
        }
    },


    GRAND_F("할아버지","GRAND_F"){
        override fun getValue(value: Any): Any {
            return "GRAND_F"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "할아버지"
            }
        }
    },

    GRAND_M("할머니","GRAND_M"){
        override fun getValue(value: Any): Any {
            return "GRAND_M"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "할머니"
            }
        }
    },

    MATERNAL_G_F("외할아버지","MATERNAL_G_F"){
        override fun getValue(value: Any): Any {
            return "MATERNAL_G_F"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "외할아버지"
            }
        }
    },

    MATERNAL_G_M("외할머니","MATERNAL_G_M"){
        override fun getValue(value: Any): Any {
            return "MATERNAL_G_M"
        }

        override fun getKorName(surveyByLevel: SurveyByLevel): String {
            when(surveyByLevel){
                SurveyByLevel.LEVEL1, SurveyByLevel.LEVEL2,
                SurveyByLevel.LEVEL3,
                SurveyByLevel.LEVEL4,
                SurveyByLevel.LEVEL5->

                    return "외할머니"
            }
        }
    };

    companion object {

        private val answerMap : HashMap<String , Any> = HashMap()

        fun convertHashMap() : HashMap<String , Any>{

            answerMap.clear()
            enumValues<FamilyMemberType>().map {
                answerMap.put(it.korName , it.value)
            }.forEach {  }

            return answerMap
        }

    }

}
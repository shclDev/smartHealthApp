package com.shcl.smarthealth.presentation.view.survey.content

object AnswerType {
    val typeFreq : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to "ALWAYS" , "보통이다" to "NORMAL" ,  "아닌편이다" to "NO" )
    val typeInteger421 : HashMap<String , Any> = hashMapOf("주 4회 이상" to 4 , "주 2~3회" to 2 ,  "주 1회 이하" to 1 )
    val typeInteger543 : HashMap<String , Any> = hashMapOf("5종류" to 5 , "4종류" to 4 ,  "3종류 이하" to 3 )
    val typeInteger521 : HashMap<String , Any> = hashMapOf("주 5회 이상" to 5 , "주 2~3회" to 2 ,  "주 1회 이하" to 1 )
    val typeInteger0123 : HashMap<String , Any> = hashMapOf("전혀 없음" to 0 , "주 1회 미만" to 1 ,  "주 1~2회" to 2 , "주 3회 이상" to 3 )
    val typeInteger1234567 : HashMap<String , Any> = hashMapOf("1회" to 1 , "2회" to 2  ,  "3회" to 3  , "4회" to 4 , "5회" to 5,"6회" to 6,"7회" to 7)
    val typeInteger10204560 : HashMap<String , Any> = hashMapOf("10분이하" to 10 , "10분~30분" to 20 , "30분~1시간" to 45 , "1시간 이상" to 60)
    val typeInteger0135 : HashMap<String , Any> = hashMapOf("주 1회 미만" to 0 , "주 1~2회" to 1 , "주 3~4회" to 3 , "주 5회 이상" to 5)

    val typeInteger135710 : HashMap<String , Any> = hashMapOf("소주 1-2잔 (맥주 355cc 1캔 반 이하, 양주 2잔, 포도주 1-2잔)" to 1,
        "소주 3-4잔 (맥주 3캔, 양주 4잔, 포도주 3-4잔)" to 3 ,
        "소주 5-6잔 (맥주 4캔 반 이하, 양주 6잔, 포도주 5-6잔)" to 5 ,
        "소주 7-9잔 (2홉 소주 1병 반 미만, 맥주 6캔, 양주 9잔, 포도주 7-9잔)" to 7,
        "소주 10잔 이상 (2홉 소주 1병 반 이상, 맥주 7캔, 양주 10잔, 포도주 10잔)" to 10,
        )

    val typeIntegerSmoking : HashMap<String , Any> = hashMapOf("전혀 피운 적이 없다(평생 10개비 미만)" to 0 , "과거에는 피웠으나 현재 피우지 않는다" to 1 ,  "현재도 피우고 있다" to 2)

    val typeGoodBad : HashMap<String , Any> = hashMapOf("매주 좋았다" to "VERY_GOOD" , "상당히 좋았다" to "GOOD" ,  "상당히 나빴다" to "BAD" , "매우 나빴다" to "VERY_BAD")
    val typeSleepGoodBad : HashMap<String , Any> = hashMapOf("매우 충분하다" to "VERY_GOOD" , "충분한 편이다" to "GOOD" ,  "불충분한 편이다" to "BAD" , "대단히 불충분하다" to "VERY_BAD")

    val typeSleep1GoodBad : HashMap<String , Any> = hashMapOf("매우 그렇다" to "VERY_GOOD" , "그렇다" to "GOOD" ,  "그렇지 않다" to "BAD" , "전혀 그렇지 않다" to "VERY_BAD")
    val typeDaysFrequency : HashMap<String , Any> = hashMapOf("전혀 그렇지 않다" to "NEVER" , "수일 정도" to "DAYS" ,  "1주일 이상" to "WEEK" , "거의 매일" to "EVERYDAY")
    val typeBoolean : HashMap<String , Any> = hashMapOf("예" to true , "아니요" to false)
    val typeNowSmoke : HashMap<String , Any > = hashMapOf("전혀 피운 적이 없다(평생 10개비 미만)" to "NEVER" , "과거에는 피웠으나 현재 피우지 않는다" to "NOT_NOW" ,"현재도 피우고 있다" to "NOW")
    val typeNowDrink : HashMap<String , Any > = hashMapOf("아니오,원래 안마신다" to "NEVER" , "아니오,전에는 마셨으나 끊었다" to "NOT_NOW" ,"예" to "NOW")


    val typeCancer : HashMap<String , Any> = hashMapOf(
        "폐암" to "LUNG_CANCER" ,
        "위암"  to "GASTRIC_CANCER" ,
        "대장암" to "COLON_CANCER" ,
        "간암" to "LIVER_CANCER",
        "갑상선암" to "THYROID_CANCER",
        "유방암" to "BREAST_CANCER",
        "기타암" to "ETC_CANCER"
    )

    val typeDisease:HashMap<String , Any> = hashMapOf(
        "고혈압" to "HIGH_BLOOD_PRESSURE" ,
        "당뇨" to "DIABETES",
        "심근경색/협십증" to "ANGINA_OR_MYOCARDIAL_INFARCTION",
        "고지혈증" to "HYPERLIPIDEMIA",
        "뇌졸증(중풍)" to "STROKE",
        "폐결핵" to "TUBERCULOSIS_OF_LUNG",
        "B형,C형 간염보유자" to "HEPATITIS",
        "골다골증" to "OSTEOPOROSIS",
        "갑상성기능항진증" to "HYPERTHYROIDISM",
        "천식" to "ASTHMA",
        "알레르기 비염" to "ALLERGIC_RHINITIS",
        "위궤양/십이지장 궤양" to "GASTRIC_ULCER_OR_DUODENAL_ULCER",
        "역류성 식도염" to "REFLUX_ESOPHAGITIS",
        "만성 신장 질환" to "CHRONIC_KIDNEY_DISEASE",
        "전립선비대증" to "BENIGN_PROSTATIC_HYPERPLASIA",
        "녹내장" to "GLAUCOMA",
        "관절염" to "ARTHRITIS",
        "기타질환" to "ETC_DISEASE",
        "없음" to "NONE"
    )

    val typeFamilyDisease:HashMap<String , Any> = hashMapOf(
        "고혈압" to "HIGH_BLOOD_PRESSURE" ,
        "당뇨" to "DIABETES",
        "심근경색/협십증" to "ANGINA_OR_MYOCARDIAL_INFARCTION",
        "뇌졸증" to "STROKE",
        "치매" to "DEMENTIA",
        "만성간염/간경변" to "CHRONIC_HEPATITIS_OR_LIVER_CIRRHOSIS",
        "폐암" to "LUNG_CANCER" ,
        "위암"  to "GASTRIC_CANCER",
        "대장,직장암" to "COLON_CANCER_OR_RECTAL_CANCER",
        "간암" to "LIVER_CANCER",
        "갑상선암" to "THYROID_CANCER",
        "췌장암" to "PANCREATIC_CANCER",
        "유방암" to "BREAST_CANCER",
        "기타암" to "ETC_CANCER",
        "해당 없음" to "NONE"
    )

    val typeLittleBig :HashMap<String , Any> = hashMapOf(
        "전혀 문제가 없었다" to 0 ,
        "매우 적은 문제가 있었다" to 1,
        "얼마간 문제가 있었다" to 2,
        "매우 큰 문제가 있었다" to 3
    )





}
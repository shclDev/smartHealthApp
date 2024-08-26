package com.shcl.smarthealth.presentation.view.introduce

enum class IntrouduceAssistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,1,"지금부터 오늘의 건강 정보를 측정해보겠습니다.",false ),
    QUESTION_2(3, 2, "그 전에 음성으로 어떻게 대화하는지 배워볼게요.",false),
    QUESTION_3(4,3, "제 말이 끝나면 지금처럼 아래에 마이크 버튼이 뜰 거예요.",false),
    QUESTION_4(5,4, "'띵' 하는 소리가 나면 음성인식이 시작된다는 뜻이예요.\n한번 해볼까요?", false ),
    QUESTION_5(6,5, "'은하야 안녕' 이라고 말해보세요!" , false),
    QUESTION_6(7,6,"오늘은 무슨 요일인가요?",false ),
    QUESTION_7(8,7,"조항요! 그럼 이제 오늘의 건강을 체크하러 가볼까요?",false),
    QUESTION_11(12,11, "11번. 외식(직장에서 제공되는 식사 제외)을 얼마나 자주 하십니까?",false);

    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<IntrouduceAssistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<IntrouduceAssistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }
    }

}
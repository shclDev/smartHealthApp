package com.shcl.smarthealth.presentation.view.introduce

enum class IntrouduceAssistant(val num : Int , val questionId : Int ,  val title : String, val autoNextPlay:Boolean){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(1,1,"지금부터 오늘의 건강 정보를 측정해보겠습니다.",true ),
    QUESTION_2(2, 2, "그 전에 음성으로 어떻게 대화하는지 배워볼게요.",true),
    QUESTION_3(3,3, "제 말이 끝나면 지금처럼 아래에 마이크 버튼이 뜰 거예요.",true),
    QUESTION_4(4,4, "'띵' 하는 소리가 나면 음성인식이 시작된다는 뜻이예요.\n한번 해볼까요?", true ),
    QUESTION_5(5,5, "'은하야 안녕' 이라고 말해보세요!" , false),
    QUESTION_6(6,6,"오늘은 무슨 요일인가요?",false),
    QUESTION_7(7,7,"좋아요! 그럼 이제 오늘의 건강을 체크하러 가볼까요?",true),
    QUESTION_11(8,8, "오늘의 건강 정보를 측정할게요.",true),
    QUESTION_12(9,9, "어젯밤 잠은 잘 주무셨나요?",false),
    QUESTION_13(10,10, "잠은 몇 시간 주무셨나요?",false),
    QUESTION_14(11,11, "오늘의 컨디션은 어떠신지 여쭤볼게요",true),
    QUESTION_15(12,12, "아주 컨디션이 좋은면 1\n아주 피곤하면 10\n오늘 얼마나 피곤하신지 1부터 10까지 숫자로 말씀해주세요.",false);


    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<IntrouduceAssistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getQuestionIdByAssistant(questionId: Int) : IntrouduceAssistant?{
            val findQuestion = enumValues<IntrouduceAssistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion
            }?:run{
                return null
            }
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
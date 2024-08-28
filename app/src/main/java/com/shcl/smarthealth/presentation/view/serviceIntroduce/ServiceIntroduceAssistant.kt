package com.shcl.smarthealth.presentation.view.serviceIntroduce

enum class ServiceIntrouduceAssistant(val num : Int , val questionId : Int ,  val title : String, val autoNextPlay:Boolean){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(1,1,"안녕하세요!",true ),
    QUESTION_2(2, 2, "만나뵙게 되어 반가워요.\n저는 은하라고 해요.",true),
    QUESTION_3(3,3, "SHCL은 편리하게 가정에서도 건강관리를 할 수 있는\n개인맞춤형 헬스케어 서비스예요.",true),
    QUESTION_4(4,4, "앞으로 주기적으로 혈압,혈당,심박수,체중 등의\n건강 상태 측정을 도와드릴게요.", true ),
    QUESTION_5(5,5, "측정을 마치면 수집된 데이터를 종합적으로 검토 후,\n일일 건강 상태에 따른 권고사항을 제공해드려요." , true),
    QUESTION_6(6,6,"꾸준히 활용할수록 상세한 건강분석도 가능하니,\n잊지 말고 건강상태를 측정해주세요.",true),
    QUESTION_7(7,7,"먼저,기본적인 건강 정보가 필요해요.\n이 과정은 서비스를 사용하시면서 최초 한 번만 진행됩니다.\n필요하신 경우 추후에 수정이 가능합니다.",true),
    QUESTION_8(8,8, "기본 건강 정보 입력을 시작할게요.\n질문을 잘 듣고 해당하는 항목을 눌러주세요.\n* 본 과정은 약 10분 정도가 소요 됩니다.",true);


    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<ServiceIntrouduceAssistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getQuestionIdByAssistant(questionId: Int) : ServiceIntrouduceAssistant?{
            val findQuestion = enumValues<ServiceIntrouduceAssistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion
            }?:run{
                return null
            }
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<ServiceIntrouduceAssistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }
    }

}
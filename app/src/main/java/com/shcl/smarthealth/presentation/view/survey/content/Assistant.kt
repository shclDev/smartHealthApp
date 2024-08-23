package com.shcl.smarthealth.presentation.view.survey.content


enum class Level1Assistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,1,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요.1번. 우유나 칼슘강화두유, 기타 유제품(요구르트 등)을 매일 1컵(200ml) 이상 마십니까?",false ),
    QUESTION_2(3, 2, "2번. 육류, 생선, 달걀, 콩, 두부 등으로 된 음식을 매일 3회 이상 먹습니까?",false),
    QUESTION_3(4,3, "3번. 김치 이외의 채소를 식사할 때마다 먹습니까?",false),
    QUESTION_4(5,4, "4번. 과일(1개)이나 과일쥬스(1잔)을 매일 먹습니까?" , false ),
    QUESTION_5(6,5, "5번. 튀김이나 볶음요리를 얼마나 자주 먹습니까?" , false),
    QUESTION_6(7,6,"6번. 콜레스테롤이 많은 식품(삼겹살, 달걀노른자, 오징어 등)을 얼마나 자주 먹습니까?",false ),
    QUESTION_7(8,7,"7번. 아이스크림, 케이크, 과자, 음료수(커피, 콜라, 식혜 등) 중 1가지를 매일 먹습니까?",false),
    QUESTION_8(9,8, "8번. 젓갈, 장아찌, 자반 등을 매일 먹습니까?",false),
    QUESTION_9(10,9, "9번. 식사를 매일 정해진 시간에 하십니까?",false),
    QUESTION_10(11,10, "10번. 곡류(밥, 빵류), 고기·생선·달걀·콩류, 채소류, 과일류, 우유류 등 총 5종류의 식품 중에서 하루에 보통 몇 종류의 식품을 드십니까?",false),
    QUESTION_11(12,11, "11번. 외식(직장에서 제공되는 식사 제외)을 얼마나 자주 하십니까?",false);

    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<Level1Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<Level1Assistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }
    }

}

enum class Level2Assistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean , val defaultNextQuestionId : Int , val jumpQuestionId : Int){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,12,"다음 질문들은 귀하의 수면습관에 관한 질문입니다.질문을 잘 듣고 해당하는 답안을 선택해주세요.1번. 지난 한 달 동안 당신이 취한 전반적인 수면의 질을 어떻게 평가 하시겠습니까?",false , 13 , 14 ),
    QUESTION_1_1(3,13,"1-1번.지난 한 달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?",false , 16 , 16 ),
    QUESTION_1_2(4,14,"1-2번.지난 한 달 동안, 잠자리에 누우신 후 잠이 들기까지 몇 분 정도 걸리셨습니까?",false ,15,15 ),
    QUESTION_1_3(5,15,"1-3번.잠자리에 든 지 30분 이내에 잠들지 못한 적이 얼마나 자주 있습니까?",false ,16,16),

    QUESTION_2(6, 16, "2번. 한밤중이나 이른 아침에 잠이 깰 때가 얼마나 자주 있습니까?",false , 17,17),
    QUESTION_3(7,17, "3번. 화장실에 가기 위해 잠이 깰 때가 얼마나 자주 있습니까?",false,18,18),
    QUESTION_4(8,18, "4번. 숨을 편안하게 쉬지 못하는 적이 얼마나 자주 있습니까?" , false ,19,19),
    QUESTION_5(9,19, "5번. 기침을 하거나 코를 크게 곤 적이 얼마나 자주 있습니까?" , false , 20,20),
    QUESTION_6(10,20,"6번. 너무 추워서 잠을 자는데 얼마나 어려움을 겪었습니까?",false ,21,21),
    QUESTION_7(11,21,"7번. 너무 더워서 잠을 자는데 얼마나 어려움을 겪었습니까?",false ,22,22),
    QUESTION_8(12,22, "8번. 악몽 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?",false,23,23,),
    QUESTION_9(13,23, "9번. 통증 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?",false,24,24),
    QUESTION_10(14,24, "10번. 잠을 자기 어려운 다른 이유가 있었습니까?",false , 27,25),
    QUESTION_10_1(15,25, "10-1번. 잠을 자기 어려운 다른 이유가 있으셨다면, 어떤 것이었는지 알려주세요.",false,26,26),
    QUESTION_10_2(16,26, "10-2번. 지난 한 달 동안,위에 해당하는 이유로 잠을 자는데 얼마나 어려움을 겪었습니까?",false,27,27),
    QUESTION_11(17,27, "11번. 지난 한 달 동안,잠자기 위해서 얼마나 자주 약을 복용하셨습니까?",false,28,28),
    QUESTION_12(18,28, "12번. 지난 한 달 동안,운전,식사,또는 사회 활동 중에 깨어 있는 것이 얼마나 자주 힘들었습니까?",false,29,29),
    QUESTION_13(19,29, "13번. 지난 한 달 동안,해야 할 일들을 열심히 해서 마치는 것이 얼마나 힘들었습니까?",false,30,30),
    QUESTION_14(20,30, "14번. 하루에 취하고 있는 수면이 피로 회복에 충분하다고 생각하십니까?",false,31,31),
    QUESTION_15(21,31, "15번. 본인께서 생각하시기에 휴식 시간을 충분히 갖는다고 생각하십니까?",false,32,32),
    QUESTION_16(22,32, "16번. 지난 한달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?",false,33,33);

    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<Level2Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<Level2Assistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getQuestion(questionId: Int) : Level2Assistant?{
            val findQuestion = enumValues<Level2Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion
            }?:run{
                return null
            }
        }

    }

}

enum class Level3Assistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean,){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,33, "다음 질문들은 귀하의 우울감에 관한 질문입니다.질문을 잘 듣고 해당하는 답안을 선택해주세요.1번. 일을 함에 있어 거의 흥미나 즐거움이 없던 적이 얼마나 있습니까?",false ),
    QUESTION_2(3,34,"2번.기분이 가라앉거나 우울하거나 희망이 없던 적이 얼마나 있습니까?",false ),
    QUESTION_3(4,35,"3번.잠이 들거나 수면을 유지하는데 문제가 있거나 수면량이 너무 많았던 적이 얼마나 있습니까?",false ),
    QUESTION_4(5,36,"4번.피로감을 느끼거나 기력이 별로 없던 적이 얼마나 있습니까?",false ),

    QUESTION_5(6, 37, "5번. 식욕이 없거나 또는 너무 과식을 한 적이 얼마나 있습니까?",false),
    QUESTION_6(7,38,"6번. 자신에 대해 죄책감을 느끼거나, 실패자라고 생각됨, 또는 자신에 대해 실망을 하거나 가족들을 실망시켰다고 생각한 적이 얼마나 있습니까?",false ),
    QUESTION_7(8,39,"7번. 일에 집중하기가 어려운 적이 얼마나 있습니까? (예: 신문읽기 또는 텔레비전 시청)",false),
    QUESTION_8(9,40, "8번. 주변사람들이 알 정도로 움직이거나 말하는 것이 느려짐, 또는 그 반대로 매우 불안하여 평상시와 다르게 안절부절 못하거나 들떠 있었던 적이 얼마나 있습니까?",false),
    QUESTION_9(10,41, "9번. 자신이 죽는 것이 낫다고 생각하거나 어떤 식으로든 자신을 해칠 것이라고 생각한 적이 얼마나 있습니까?",false),
    QUESTION_10(11,42, "10번. 이러한 문제들로 인해서 당신은 일을 하거나 가정 일을 돌보거나 다른 사람과 어울리는 것이 얼마나 어려웠습니까?",false);

    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<Level3Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<Level3Assistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }
    }

}

enum class Level4Assistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean,val defaultNextQuestionId : Int , val jumpQuestionId : Int , val otherJumpQuestionId: Int){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,43, "다음 질문들은 귀하의 흡연 및 음주에 관한 질문입니다.질문을 잘 듣고 해당하는 답안을 선택해주세요.1번. 현재 담배를 피우십니까?",false, 48,44 , 46 ),
    QUESTION_1_1(3,44, "1-1번.담배를 언제 끊었습니까?",false , 45,45,45),
    QUESTION_1_2(4,45, "1-2번.끊기 전 2년 동안 하루 평균 몇 개비를 피웠습니까?",false ,48,48 ,48 ),
    QUESTION_1_3(5,46, "1-3번.처음 담배를 피우기 시작한 연령은 몇 세입니까?",false ,47,47,47),
    QUESTION_1_4(6,47, "1-4번.현재 하루 평균 몇 개비의 담배를 피우십니까?",false ,48,48,48),

    QUESTION_2(7,48,"2번.현재 술을 드십니까?",false , 52,49 ,49),
    QUESTION_2_1(8,49,"2-1번.처음 술을 드시기 시작한 연령은 몇 세입니까?",false ,50,50,50),
    QUESTION_2_2(9,50,"2-2번.술을 마시는 횟수는 보통 어느 정도입니까?",false ,51,51,51),
    QUESTION_2_3(10,51,"2-3번.술을 드시는 날은 보통 몇 잔을 드십니까? (*소주, 양주, 포도주 구분없이 각각의 술잔으로 계산합니다. 단 맥주는 캔맥주 1개 (355cc)=1.4잔)",false,52 ,52,52);


    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<Level4Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<Level4Assistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getQuestion(questionId: Int) : Level4Assistant?{
            val findQuestion = enumValues<Level4Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion
            }?:run{
                return null
            }
        }
    }

}

enum class Level5Assistant(val num : Int , val questionId : Int ,  val title : String, val voice:Boolean){

    //INTRO(1,0,"지금부터 기본 건강 설문을 진행하겠습니다.첫 번째로, 귀하의 식습관에 관하여 질문하겠습니다.질문을 잘 듣고 해당하는 답안을 선택해주세요." , false ),
    QUESTION_1(2,52, "다음 질문들은 귀하의 신체활동에 관한 질문입니다.질문을 잘 듣고 해당하는 답안을 선택해주세요.1번. 지난 7일간 무거운 물건 나르기, 달리기, 에어로빅, 빠른 속도로 자전거 타기 등과 같은 격렬한 신체 활동을 며칠간 하셨습니까?",false ),
    QUESTION_1_2(3,53, "2번.그런 날 중 하루에 격렬한 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",false ),
    QUESTION_2(4,54, "3번.지난 7일간 가벼운 물건 나르기, 보통 속도로 자전거 타기, 복식 테니스 등과 같은 중간 정도 신체 활동을 며칠간 하였습니까? 걷기는 포함시키지 마십시오",false ),
    QUESTION_2_1(5,55, "4번.그런 날 중 하루에 중간 정도의 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",false ),
    QUESTION_3(6,56, "5번.지난 7일간, 한 번에 적어도 10분 이상 걸은 날이 며칠입니까?",false ),

    QUESTION_3_1(7,57,"6번.그런 날 중 하루에 걸으면서 보낸 시간이 보통 얼마나 됩니까?",false ),
    QUESTION_4(8,58,"7번.지난 7일간, 주중에 앉아서 보낸 시간이 보통 얼마나 됩니까?",false ),
    QUESTION_5(9,59,"8번.다음 암을 진단받은 적이 있습니까?",false ),
    QUESTION_5_1(10,60,"다음의 추가 질문에 응답해주세요.",false ),
    QUESTION_6(11,67,"9번.지금까지 아래의 질병이 있다고 진단받은 적이 있습니까?",false ),
    QUESTION_6_1(12,68,"다음의 추가 질문에 응답해주세요.",false ),
    QUESTION_7(13,87,"10번.부모,형제,자매,삼촌 이내의 가까운 친적 중 아래의 질병을 앓았거나 그로 인해 사망한 경우가 있습니까?",false),
    QUESTION_7_1(14,88,"다음의 추가 질문에 응답해주세요.",false);


    companion object{

        fun getVoiceByQuestionId(questionId: Int) : String?{
            val findQuestion = enumValues<Level5Assistant>().find { it.questionId == questionId}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }

        fun getVoiceByNum(num: Int) : String?{
            val findQuestion = enumValues<Level5Assistant>().find { it.num == num}

            findQuestion?.let {
                return findQuestion.title
            }
            return null
        }
    }

}
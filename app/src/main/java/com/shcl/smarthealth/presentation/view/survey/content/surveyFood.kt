package com.shcl.smarthealth.presentation.view.survey.content

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shcl.smarthealth.domain.model.remote.survey.Question
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.FrequencyType
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel
import com.shcl.smarthealth.presentation.view.survey.SurveyViewModel
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeFreq
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger421
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger521
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger543
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveyFood(viewModel : SurveyViewModel) {
    val scrollState = rememberScrollState()
    val questions by viewModel.questions.collectAsStateWithLifecycle()

    val checkImageIcon = Icons.Default.CheckCircle

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        NumberButton("1")
        Text("우유나 칼슘 강화 두유,기타 유제품(요구르트 등)을 매일 1컵(200ml) 이상 마십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)

        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1) ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(1, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }
            }
        )

        NumberButton("2")
        Text("육류,생선,달걀,콩,두부 등으로 된 음식을 매일 3회 이상 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1) ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->
                var answer = Utils.getAnswer(2, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }
            }
        )

        NumberButton("3")
        Text("김치 이외의 채소를 식사할 때마다 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1) ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(3, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }


                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("4")
        Text("과일(1개)이나 과일쥬스(1잔)을 매일 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1) ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(4, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }


                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("5")
        Text("튀김이나 볶음 요리를 얼마나 자주 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger421,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->


                var answer = Utils.getAnswer(5, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("6")
        Text("콜레스테롤이 많은 식품(삼겹살,달걀노른자,오징어 등)을 얼마나 자주 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger421,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(6, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("7")
        Text("아이스크림,케이크,과자,음료수(커피,콜라,식혜 등)중 1가지를 매일 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(7, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("8")
        Text("젓갈,장아찌,자반 등을 매일 먹습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(8, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("9")
        Text("식사를 매일 정해진 시간에 하십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = FrequencyType.convertHashMap(SurveyByLevel.LEVEL1),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(9, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("10")
        Text("곡류(밥, 빵류), 고기·생선·달걀·콩류, 채소류, 과일류, 우유류 등 총 5종류의 식품 중에서 하루에 보통 몇 종류의 식품을 드십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger543,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(10, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        NumberButton("11")
        Text("외식(직장에서 제공되는 식사 제외)을 얼마나 자주 하십니까?" ,style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700 )
        CustomGroupButtons(
            options = typeInteger521,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                var answer = Utils.getAnswer(11, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel1Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

    }

}
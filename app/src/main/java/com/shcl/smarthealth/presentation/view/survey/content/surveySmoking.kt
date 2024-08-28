package com.shcl.smarthealth.presentation.view.survey.content

import android.util.Log
import android.view.View
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.remote.survey.answer.dtoType.DurationFrequencyDto
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.DurationType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.LittleBigType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.NowType
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomComboBox
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.ui.common.CustomRadioButton
import com.shcl.smarthealth.presentation.ui.common.CustomTextField
import com.shcl.smarthealth.presentation.ui.common.CustomTwoComboBox
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel
import com.shcl.smarthealth.presentation.view.survey.SurveyViewModel
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeGoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0123
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger135710
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeIntegerSmoking
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeLittleBig

import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeNowDrink
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeNowSmoke
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleep1GoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleepGoodBad
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color94918A
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography
import java.util.Arrays

@Composable
fun surveySmoking(viewModel : SurveyViewModel){



    val scrollState = rememberScrollState()
    val questions by viewModel.questions.collectAsStateWithLifecycle()

    var question1_1_year_answer by remember { mutableStateOf("") }
    var question1_1_month_answer by remember { mutableStateOf("") }
    var question1_2_answer by remember { mutableStateOf("") }
    var question1_3_answer by remember { mutableStateOf("") }
    var question1_4_answer by remember { mutableStateOf("") }
    var question2_2_answer by remember { mutableStateOf("") }


    var question1_1_visible by remember { mutableStateOf(false) }
    var question1_3_visible by remember { mutableStateOf(false) }
    var question2_1_visible by remember { mutableStateOf(false) }

    val checkImageIcon = Icons.Default.CheckCircle


    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        NumberButton("1")
        Text("현재 담배를 피우십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = NowType.convertHashMap(SurveyByLevel.LEVEL4) ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->
                if(value.toString().compareTo("NOT_NOW") == 0 ){
                    question1_1_visible = true
                }else{
                    question1_1_visible = false
                }

                if(value.toString().compareTo("NOW") == 0){
                    question1_3_visible = true
                }else{
                    question1_3_visible = false
                }

                var answer = Utils.getAnswer(43, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel4Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        AnimatedVisibility (
            visible = question1_1_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ){
            Column {
                NumberButton("1-1")
                Text(
                    "담배를 언제 끊었습니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )
                CustomTwoComboBox(
                    subject = "",
                    firstList = Utils.makeYearRange(1920, 2024),
                    secondList = Arrays.asList(
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12"
                    ),
                    firstSelected = { value ->
                        question1_1_year_answer = value.toString()

                        if (!question1_1_month_answer.isNullOrEmpty() && !question1_1_year_answer.isNullOrEmpty()) {
                            var answer = Utils.getAnswer(44, questions)

                            answer?.let {
                                answer.answer =
                                    "${question1_1_year_answer}-${question1_1_month_answer}"
                                viewModel.addLevel4Answer(answer)
                            }
                        }

                        Log.d("smarthealth", "f selected")
                    },
                    firstUnit = "년",
                    secondUnit = "월",
                    secondSelected = { value ->
                        question1_1_month_answer = value.toString()

                        if (!question1_1_month_answer.isNullOrEmpty() && !question1_1_year_answer.isNullOrEmpty()) {
                            var answer = Utils.getAnswer(44, questions)

                            answer?.let {
                                answer.answer =
                                    "${question1_1_year_answer}-${question1_1_month_answer}"
                                viewModel.addLevel4Answer(answer)
                            }
                        }

                        Log.d("smarthealth", "2 selected")
                    }
                )

                NumberButton("1-2")
                Text(
                    "끊기 전 2년 동안 하루 평균 몇 개비를 피웠습니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(20f.pxToDp()))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomTextField(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 383f.pxToDp(), minHeight = 75f.pxToDp()),
                        focusedBoardColor = Color333333,
                        unfocusedBoardColor = ColorD4D9E1,
                        isHiddenText = false,
                        placeHolder = "",
                        keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                        valueChanged = {
                                value->
                                question1_2_answer = value
                                var answer = Utils.getAnswer(45, questions)

                                answer?.let {
                                    answer.answer = Integer.parseInt(value)
                                    viewModel.addLevel4Answer(answer)
                            }
                        }
                    )
                    /*
                    TextField(
                        singleLine = true,
                        colors = TextFieldColors(unfocusedContainerColor = Color.White , focusedContainerColor = Color.White),
                        shape = RoundedCornerShape(10f.pxToDp()),
                        readOnly = false,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 383f.pxToDp(), minHeight = 75f.pxToDp())
                            .background(Color.White)
                            .border(width = 2f.pxToDp(), color = Color94918A),
                        value = question1_2_answer,
                        onValueChange = { value->
                            question1_2_answer = value
                            var answer = Utils.getAnswer(45, questions)

                                answer?.let {
                                    answer.answer = value
                                    viewModel.addLevel4Answer(answer)
                                }
                            })*/
                    Spacer(modifier = Modifier.width(20f.pxToDp()))
                    Text("개비" , fontSize = 20f.pxToSp())
                }
            }
        }


        AnimatedVisibility (
            visible = question1_3_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Column {
                NumberButton("1-3")
                Text(
                    "처음 담배를 피우기 시작한 연령은 몇 세입니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )

                CustomComboBox(
                    subject = "만", firstUnit = "세", list = Utils.makeAgeRange(10,70) ,
                    selected = { value->
                        var answer = Utils.getAnswer(46, questions)
                        answer?.let {
                            answer.answer = Integer.parseInt(value.toString())
                            viewModel.addLevel4Answer(answer)
                        }
                })

                NumberButton("1-4")
                Text(
                    "현재 하루 평균 몇 개비의 담배를 피우십니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(20f.pxToDp()))
                Row {
                    CustomTextField(
                        modifier = Modifier
                            .defaultMinSize(minHeight = 85f.pxToDp() , minWidth = 383f.pxToDp())
                        ,
                        keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                        focusedBoardColor = Color143F91,
                        unfocusedBoardColor = ColorD4D9E1,
                        placeHolder = "여기에 입력해주세요",
                        valueChanged = { value->
                            question1_4_answer = value
                            var answer = Utils.getAnswer(47, questions)

                            answer?.let {
                                answer.answer = Integer.parseInt(value.toString())
                                viewModel.addLevel4Answer(answer)
                            }
                        })
                    /*
                    TextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        readOnly = false,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 383f.pxToDp(), minHeight = 75f.pxToDp())
                            .background(Color.White)
                            .border(width = 2f.pxToDp(), color = Color94918A),
                        shape = RoundedCornerShape(10f.pxToDp()),
                        value = question1_4_answer, onValueChange = { value->
                            question1_4_answer = value
                            var answer = Utils.getAnswer(47, questions)

                            answer?.let {
                                answer.answer = Integer.parseInt(value.toString())
                                viewModel.addLevel4Answer(answer)
                            }
                        })*/
                    Spacer(modifier = Modifier.width(35f.pxToDp()))
                    Text("개비")
                }
            }
        }

        NumberButton("2")
        Text("현재 술을 드십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = NowType.convertHashMap(SurveyByLevel.LEVEL4, otherName = true),
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { value->

                if(value.toString().compareTo("NOW") == 0){
                    question2_1_visible = true
                }else{
                    question2_1_visible = false
                }

                var answer = Utils.getAnswer(48, questions)

                answer?.let {
                    answer.answer = value
                    viewModel.addLevel4Answer(answer)
                }

                Log.d("survey" , "answer : ${value}")
            }
        )

        AnimatedVisibility (
            visible = question2_1_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Column {
                NumberButton("2-1")
                Spacer(modifier = Modifier.height(15f.pxToDp()))
                Text(
                    "처음 술을 드시기 시작한 연령은 몇 세입니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )

                CustomComboBox(
                    subject = "만", firstUnit = "세", list = Utils.makeAgeRange(10,70) ,
                    selected = { value->
                        var answer = Utils.getAnswer(49, questions)
                        answer?.let {
                            answer.answer = Integer.parseInt(value.toString())
                            viewModel.addLevel4Answer(answer)
                        }
                    })

                NumberButton("2-2")
                Text(
                    "술을 마시는 횟수는 보통 어느 정도입니까?",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomRadioButton(
                        options = DurationType.convertHashMap(SurveyByLevel.LEVEL4),
                        selectionChanged = { value ->

                            question2_2_answer = value.toString()

                            /*
                            var answer = Utils.getAnswer(50, questions)

                            answer?.let {
                                answer.answer = value
                                viewModel.addLevel4Answer(answer)
                            }*/

                            Log.d("survey", "answer : ${value}")
                        }
                    )

                    CustomTextField(
                        modifier = Modifier.defaultMinSize(
                            minWidth = 290f.pxToDp(),
                            minHeight = 86f.pxToDp()
                        ),
                        keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                        focusedBoardColor = Color143F91,
                        unfocusedBoardColor = ColorD4D9E1,
                        placeHolder = "여기에 입력해주세요",
                        valueChanged = { value->
                            var answer = Utils.getAnswer(50, questions)

                            var durationFrequencyDto = DurationFrequencyDto(
                                durationType = question2_2_answer,
                                value = Integer.parseInt(value)
                            )

                            answer?.let {
                                answer.answer = durationFrequencyDto
                                viewModel.addLevel4Answer(answer)
                            }
                        })

                    Text("회", style = Typography.labelSmall , fontSize = 20f.pxToSp())
                }

                /*
                CustomGroupButtons(
                    options = DurationType.convertHashMap(SurveyByLevel.LEVEL4),
                    unSelectedColor = ColorD4D9E1,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    icon = checkImageIcon,
                    selectionChanged = { value ->

                        var answer = Utils.getAnswer(50, questions)

                        answer?.let {
                            answer.answer = value
                            viewModel.addLevel4Answer(answer)
                        }

                        Log.d("survey", "answer : ${value}")
                    }
                )*/

                NumberButton("2-3")
                Text(
                    "술을 드시는 날은 보통 몇 잔을 드십니까? (*소주, 양주, 포도주 구분없이 각각의 술잔으로 계산합니다. 단 맥주는 캔맥주 1개 (355cc)=1.4잔)",
                    style = Typography.headlineMedium,
                    fontSize = 30f.pxToSp(),
                    fontWeight = FontWeight.W700
                )
                CustomGroupButtons(
                    options = typeInteger135710,
                    unSelectedColor = ColorD4D9E1,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    icon = checkImageIcon,
                    selectionChanged = { value ->

                        var answer = Utils.getAnswer(51, questions)

                        answer?.let {
                            answer.answer = value
                            viewModel.addLevel4Answer(answer)
                        }

                        Log.d("survey", "answer : ${value}")
                    }
                )
            }
        }
    }


}
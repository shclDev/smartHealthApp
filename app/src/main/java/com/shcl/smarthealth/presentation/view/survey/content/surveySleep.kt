package com.shcl.smarthealth.presentation.view.survey.content

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.transition.Visibility
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.GoodBadType
import com.shcl.smarthealth.domain.model.remote.survey.answer.enumType.LittleBigType
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.ui.common.CustomTwoComboBox
import com.shcl.smarthealth.presentation.view.survey.SurveyByLevel
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeGoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0123
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0135
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger10204560
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeLittleBig
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleep1GoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleepGoodBad
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color94918A
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography
import java.util.Arrays

@Composable
fun surveySleep() {


    val scrollState = rememberScrollState()
    //val type_freq : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to 2 , "보통이다" to 1 ,  "아닌편이다" to 0 )
    //val question2 : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to 2 , "보통이다" to 1 ,  "아닌편이다" to 0 )
    var question1Answer by remember { mutableStateOf(0) }
    var question2Answer by remember { mutableStateOf(0) }
    var question3Answer by remember { mutableStateOf(0) }
    var question4Answer by remember { mutableStateOf(0) }
    var question5Answer by remember { mutableStateOf(0) }
    var question6Answer by remember { mutableStateOf(0) }
    var question7Answer by remember { mutableStateOf(0) }
    var question8Answer by remember { mutableStateOf(0) }
    var question9Answer by remember { mutableStateOf(0) }
    var question10Answer by remember { mutableStateOf(0) }
    var question11Answer by remember { mutableStateOf(0) }
    var question10_1Answer by remember { mutableStateOf("") }

    var question1_1_visible by remember { mutableStateOf(false) }
    var question1_2_visible by remember { mutableStateOf(false) }
    var question_10_1_visible by remember { mutableStateOf(false) }

    val checkImageIcon = Icons.Default.CheckCircle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        NumberButton("1")
        Text("지난 한 달 동안 당신이 취한 전반적인 수면의 질을 어떻게 평가하시겠습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(

            options = GoodBadType.convertHashMap(SurveyByLevel.LEVEL2),
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                if ("VERY_GOOD".compareTo(it.toString()) == 0 || "GOOD".compareTo(it.toString()) == 0) {
                    question1_1_visible = true
                    question1_2_visible =false
                } else {
                    question1_1_visible = false
                    question1_2_visible = true
                }
                Log.d("survey", "answer : ${it}")
            }
        )



        AnimatedVisibility(
            visible = question1_1_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))

        ) {

            Column {
                NumberButton("1-1")
                Text("지난 한 달 동안,보통 몇시에 잠자리에 들고 몇 시에 일어나셨습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
                CustomTwoComboBox(
                    subject = "취침 시각",
                    firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
                    secondList = Arrays.asList("00","30"),
                    firstSelected = { Log.d("smarthealth","f selected") },
                    firstUnit = "시",
                    secondUnit = "분",
                    secondSelected = {Log.d("smarthealth","2 selected") }
                )

                CustomTwoComboBox(
                    subject = "기상 시각",
                    firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
                    secondList = Arrays.asList("00","30"),
                    firstSelected = { Log.d("smarthealth","f selected") },
                    firstUnit = "시",
                    secondUnit = "분",
                    secondSelected = {Log.d("smarthealth","2 selected") }
                )
            }
        }

        AnimatedVisibility(
            visible = question1_2_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))

        ) {

            Column {
                NumberButton("1-2")
                Text("지난 한 달 동안,잠자리에 누우신 후 잠이 들기까지 몇 분 정도 걸리셨습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
                CustomGroupButtons(
                    options = typeInteger10204560,
                    unSelectedColor = ColorD4D9E1,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    icon = checkImageIcon,
                    selectionChanged = { it ->
                        if ("VERY_GOOD".compareTo(it.toString()) == 0 || "GOOD".compareTo(it.toString()) == 0) {
                            question1_1_visible = true
                        } else {
                            question1_1_visible = false
                        }
                        Log.d("survey", "answer : ${it}")
                    }
                )

                NumberButton("1-3")
                Text("잠자리에 든 지 30분 이내에 잠들지 못한 적이 얼마나 자주 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
                CustomGroupButtons(
                    options = typeInteger0135,
                    unSelectedColor = ColorD4D9E1,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    icon = checkImageIcon,
                    selectionChanged = { it ->
                        if ("VERY_GOOD".compareTo(it.toString()) == 0 || "GOOD".compareTo(it.toString()) == 0) {
                            question1_1_visible = true
                        } else {
                            question1_1_visible = false
                        }
                        Log.d("survey", "answer : ${it}")
                    }
                )
            }
        }

        NumberButton("2")
        Text("한밤중이나 이른 아침에 잠이 깰 때가 얼마나 자주 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("3")
        Text("화장실에 가기 위해 잠이 깰 때가 얼마나 자주 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("4")
        Text("숨을 편안하게 쉬지 못하는 적이 얼마나 자주 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("5")
        Text("기침을 하거나 코를 크게 곤 적이 얼마나 자주 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("6")
        Text("너무 추워서 잠을 자는데 얼마나 어려움을 겪었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("7")
        Text("너무 더워서 잠을 자는데 얼마나 어려움을 겪었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("8")
        Text("악몽 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("9")
        Text("통증 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("10")
        Text("잠을 자기 어려운 다른 이유가 있었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeBoolean,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                if(it as Boolean){
                    question_10_1_visible = true
                }else{
                    question_10_1_visible = false
                }
                Log.d("survey", "answer : ${it}")
            }
        )

        AnimatedVisibility(
            visible = question_10_1_visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))

        ) {

            Column {
                NumberButton("10-1")
                Text("잠을 자기 어려운 다른 이유가 있으셨다면, 어떤 것이었는지 알려주세요",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
                TextField(
                    readOnly = false,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10f.pxToDp()))
                        .border(width = 2f.pxToDp(), color = Color94918A),
                    value = "$question10_1Answer" , onValueChange = { question10_1Answer = it})

                NumberButton("10-2")
                Text("지난 한 달 동안, 위에 해당하는 이유로 잠을 자는데 얼마나 어려움을 겪었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
                CustomGroupButtons(
                    questionId = 26,
                    options = typeInteger0135,
                    unSelectedColor = ColorD4D9E1,
                    selectedColor = Color143F91,
                    containerColor = Color.White,
                    icon = checkImageIcon,
                    selectionChanged = { it ->
                        Log.d("survey", "answer : ${it}")
                    }
                )
            }
        }



        NumberButton("11")
        Text("지난 한 달 동안, 잠자기 위해서 얼마나 자주 약을 복용하셨습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("12")
        Text("지난 한 달 동안, 운전, 식사, 또는 사회 활동 중에 깨어 있는 것이 얼마나 자주 힘들었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("13")
        Text("지난 한 달 동안, 해야 할 일들을 열심히 해서 마치는 것이 얼마나 힘들었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = LittleBigType.convertHashMap(SurveyByLevel.LEVEL2),
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("14")
        Text("하루에 취하고 있는 수면이 피로 회복에 충분하다고 생각하십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = GoodBadType.convertHashMap(SurveyByLevel.LEVEL2),
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("15")
        Text("본인께서 생각하시기에 휴식 시간을 충분히 갖는다고 생각하십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = GoodBadType.convertHashMap(SurveyByLevel.LEVEL2),
            unSelectedColor = ColorD4D9E1,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it ->
                Log.d("survey", "answer : ${it}")
            }
        )

        NumberButton("16")
        Text("지난 한달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomTwoComboBox(
            subject = "취침 시각",
            firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
            secondList = Arrays.asList("00","30"),
            firstSelected = { Log.d("smarthealth","f selected") },
            firstUnit = "시",
            secondUnit = "분",
            secondSelected = {Log.d("smarthealth","2 selected") }
        )

        CustomTwoComboBox(
            subject = "기상 시각",
            firstList =  Arrays.asList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"),
            secondList = Arrays.asList("00","30"),
            firstSelected = { Log.d("smarthealth","f selected") },
            firstUnit = "시",
            secondUnit = "분",
            secondSelected = {Log.d("smarthealth","2 selected") }
        )

    }

}

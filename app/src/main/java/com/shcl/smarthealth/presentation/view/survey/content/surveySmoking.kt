package com.shcl.smarthealth.presentation.view.survey.content

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
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
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveySmoking(){



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


    val checkImageIcon = Icons.Default.CheckCircle

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        NumberButton("1")
        Text("현재 담배를 피우십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeNowSmoke ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("2")
        Text("담배를 언제 끊었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("3")
        Text("끊기 전 2년 동안 하루 평균 몇 개비를 피웠습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("4")
        Text("현재 담배를 피우십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeIntegerSmoking ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("5")
        Text("처음 담배를 피우기 시작한 연령은 몇 세입니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("6")
        Text("현재 하루 평균 몇 개비의 담배를 피우십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("7")
        Text("현재 술을 드십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeNowDrink,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("8")
        Text("술을 드시는 날은 보통 몇 잔을 드십니까? (*소주, 양주, 포도주 구분없이 각각의 술잔으로 계산합니다. 단 맥주는 캔맥주 1개 (355cc)=1.4잔)",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger135710,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("9")
        Text("처음 술을 드시기 시작한 연령은 몇 세입니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("10")
        Text("술을 마시는 횟수는 보통 어느 정도입니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeBoolean,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("11")
        Text("지난 한 달 동안, 잠자기 위해서 얼마나 자주 약을 복용하셨습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("12")
        Text("지난 한 달 동안, 운전, 식사, 또는 사회 활동 중에 깨어 있는 것이 얼마나 자주 힘들었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger0123,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("13")
        Text("지난 한 달 동안, 해야 할 일들을 열심히 해서 마치는 것이 얼마나 힘들었습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeLittleBig,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("14")
        Text("하루에 취하고 있는 수면이 피로 회복에 충분하다고 생각하십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options =  typeSleepGoodBad,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("15")
        Text("본인께서 생각하시기에 휴식 시간을 충분히 갖는다고 생각하십니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options =  typeSleep1GoodBad,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("16")
        Text("지난 한달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?" ,style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options =  typeSleep1GoodBad,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

    }


}
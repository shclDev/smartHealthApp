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
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeCancer
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeDisease
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeFamilyDisease
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeGoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0123
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger1234567
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeLittleBig
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleep1GoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleepGoodBad
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveyAct(){


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

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        NumberButton("1")
        Text("지난 7일간 무거운 물건 나르기, 달리기, 에어로빅, 빠른 속도로 자전거 타기 등과 같은 격렬한 신체 활동을 며칠간 하셨습니까?" , style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger1234567 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("2")
        Text("그런 날 중 하루에 격렬한 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
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
        Text("지난 7일간 가벼운 물건 나르기, 보통 속도로 자전거 타기, 복식 테니스 등과 같은 중간 정도 신체 활동을 며칠간 하였습니까? 걷기는 포함시키지 마십시오" ,style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger1234567 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("4")
        Text("그런 날 중 하루에 중간 정도의 신체활동을 하면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
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

        NumberButton("5")
        Text("지난 7일간, 한 번에 적어도 10분 이상 걸은 날이 며칠입니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeInteger1234567,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("6")
        Text("그런 날 중 하루에 걸으면서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
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
        Text("지난 7일간, 주중에 앉아서 보낸 시간이 보통 얼마나 됩니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
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

        NumberButton("8")
        Text("다음 암을 진단받은 적이 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeCancer,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("9")
        Text("지금까지 아래의 질병이 있다고 진단받은 적이 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDisease,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("10")
        Text("부모, 형제, 자매, 삼촌 이내의 가까운 친척 중 아래의 질병을 앓았거나 그로 인해 사망한 경우가 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeFamilyDisease,
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
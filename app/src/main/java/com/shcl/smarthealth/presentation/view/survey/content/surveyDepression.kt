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
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeDaysFrequency
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeFreq
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger421
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger521
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger543
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun surveyDepression() {

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
        Text("일을 함에 있어 거의 흥미나 즐거움이 없던 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)

        CustomGroupButtons(
            options = typeDaysFrequency ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("2")
        Text("기분이 가라앉거나 우울하거나 희망이 없던 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)

        CustomGroupButtons(
            options = typeDaysFrequency ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("3")
        Text("잠이 들거나 수면을 유지하는데 문제가 있거나 수면량이 너무 많았던 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("4")
        Text("피로감을 느끼거나 기력이 별로 없던 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("5")
        Text("식욕이 없거나 또는 너무 과식을 한 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("6")
        Text("자신에 대해 죄책감을 느끼거나, 실패자라고 생각됨, 또는 자신에 대해 실망을 하거나 가족들을 실망시켰다고 생각한 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("7")
        Text("일에 집중하기가 어려운 적이 얼마나 있습니까? (예: 신문읽기 또는 텔레비전 시청)",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("8")
        Text("주변사람들이 알 정도로 움직이거나 말하는 것이 느려짐, 또는 그 반대로 매우 불안하여 평상시와 다르게 안절부절 못하거나 들떠 있었던 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("9")
        Text("자신이 죽는 것이 낫다고 생각하거나 어떤 식으로든 자신을 해칠 것이라고 생각한 적이 얼마나 있습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        NumberButton("10")
        Text("이러한 문제들로 인해서 당신은 일을 하거나 가정 일을 돌보거나 다른 사람과 어울리는 것이 얼마나 어려웠습니까?",style = Typography.headlineMedium , fontSize = 30f.pxToSp() , fontWeight = FontWeight.W700)
        CustomGroupButtons(
            options = typeDaysFrequency,
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
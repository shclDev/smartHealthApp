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
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeBoolean
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeGoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger0123
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeLittleBig
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleep1GoodBad
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeSleepGoodBad
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

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


    val checkImageIcon = Icons.Default.CheckCircle

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        Text("1" , style = Typography.headlineMedium)
        Text("지난 한 달 동안 당신이 취한 전반적인 수면의 질을 어떻게 평가하시겠습니까?")
        CustomGroupButtons(
            options = typeGoodBad ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("2" , style = Typography.headlineMedium)
        Text("한밤중이나 이른 아침에 잠이 깰 때가 얼마나 자주 있습니까?")
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

        Text("3" , style = Typography.headlineMedium)
        Text("화장실에 가기 위해 잠이 깰 때가 얼마나 자주 있습니까?")
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

        Text("4" , style = Typography.headlineMedium)
        Text("숨을 편안하게 쉬지 못하는 적이 얼마나 자주 있습니까?")
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

        Text("5" , style = Typography.headlineMedium)
        Text("기침을 하거나 코를 크게 곤 적이 얼마나 자주 있습니까?")
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

        Text("6" , style = Typography.headlineMedium)
        Text("너무 추워서 잠을 자는데 얼마나 어려움을 겪었습니까?")
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

        Text("7" , style = Typography.headlineMedium)
        Text("너무 더워서 잠을 자는데 얼마나 어려움을 겪었습니까?")
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

        Text("8" , style = Typography.headlineMedium)
        Text("악몽 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?")
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

        Text("9" , style = Typography.headlineMedium)
        Text("통증 때문에 잠을 자는데 얼마나 어려움을 겪었습니까?")
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

        Text("10" , style = Typography.headlineMedium)
        Text("잠을 자기 어려운 다른 이유가 있었습니까?")
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

        Text("11" , style = Typography.headlineMedium)
        Text("지난 한 달 동안, 잠자기 위해서 얼마나 자주 약을 복용하셨습니까?")
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

        Text("12" , style = Typography.headlineMedium)
        Text("지난 한 달 동안, 운전, 식사, 또는 사회 활동 중에 깨어 있는 것이 얼마나 자주 힘들었습니까?")
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

        Text("13" , style = Typography.headlineMedium)
        Text("지난 한 달 동안, 해야 할 일들을 열심히 해서 마치는 것이 얼마나 힘들었습니까?")
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

        Text("14" , style = Typography.headlineMedium)
        Text("하루에 취하고 있는 수면이 피로 회복에 충분하다고 생각하십니까?")
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

        Text("15" , style = Typography.headlineMedium)
        Text("본인께서 생각하시기에 휴식 시간을 충분히 갖는다고 생각하십니까?")
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

        Text("16" , style = Typography.headlineMedium)
        Text("지난 한달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?")
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
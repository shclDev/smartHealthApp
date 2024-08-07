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
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeFreq
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger421
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger521
import com.shcl.smarthealth.presentation.view.survey.content.AnswerType.typeInteger543
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveyFood() {
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
        Text("우유나 칼슘 강화 두유,기타 유제품(요구르트 등)을 매일 1컵(200ml) 이상 마십니까?")

        CustomGroupButtons(
            options = typeFreq ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("2" , style = Typography.headlineMedium)
        Text("육류,생선,달걀,콩,두부 등으로 된 음식을 매일 3회 이상 먹습니까?")
        CustomGroupButtons(
            options = typeFreq ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("3" , style = Typography.headlineMedium)
        Text("김치 이외의 채소를 식사할 때마다 먹습니까?")
        CustomGroupButtons(
            options = typeFreq ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("4" , style = Typography.headlineMedium)
        Text("과일(1개)이나 과일쥬스(1잔)을 매일 먹습니까?")
        CustomGroupButtons(
            options = typeFreq ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("5" , style = Typography.headlineMedium)
        Text("튀김이나 볶음 요리를 얼마나 자주 먹습니까?")
        CustomGroupButtons(
            options = typeInteger421,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("6" , style = Typography.headlineMedium)
        Text("콜레스테롤이 많은 식품(삼겹살,달걀노른자,오징어 등)을 얼마나 자주 먹습니까?")
        CustomGroupButtons(
            options = typeInteger421,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("7" , style = Typography.headlineMedium)
        Text("아이스크림,케이크,과자,음료수(커피,콜라,식혜 등)중 1가지를 매일 먹습니까?")
        CustomGroupButtons(
            options = typeFreq,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("8" , style = Typography.headlineMedium)
        Text("젓갈,장아찌,자반 등을 매일 먹습니까?")
        CustomGroupButtons(
            options = typeFreq,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("9" , style = Typography.headlineMedium)
        Text("식사를 매일 정해진 시간에 하십니까?")
        CustomGroupButtons(
            options = typeFreq,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("10" , style = Typography.headlineMedium)
        Text("곡류(밥, 빵류), 고기·생선·달걀·콩류, 채소류, 과일류, 우유류 등 총 5종류의 식품 중에서 하루에 보통 몇 종류의 식품을 드십니까?")
        CustomGroupButtons(
            options = typeInteger543,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            containerColor = Color.White,
            icon = checkImageIcon,
            selectionChanged = { it->
                Log.d("survey" , "answer : ${it}")
            }
        )

        Text("11" , style = Typography.headlineMedium)
        Text("외식(직장에서 제공되는 식사 제외)을 얼마나 자주 하십니까?")
        CustomGroupButtons(
            options = typeInteger521,
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
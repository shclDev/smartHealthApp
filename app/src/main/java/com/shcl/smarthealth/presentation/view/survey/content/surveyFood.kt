package com.shcl.smarthealth.presentation.view.survey.content

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveyFood() {

    val question1 : HashMap<String , Any> = hashMapOf("항상 그런 편이다" to 4 , "보통이다" to 3 ,  "아닌편이다" to 1 )
    var question1Answer by remember { mutableStateOf(0) }
    var question2Answer by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("1" , style = Typography.headlineMedium)
        Text("우유나 칼슘 강화 두유,기타 유제품(요구르트 등)을 매일 1컵(200ml) 이상 마십니까?")
        CustomGroupButtons(
            options = question1 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            selectionChanged = { it->
                question1Answer = Integer.parseInt(it.toString())
                Log.d("survey" , "answer : $question1Answer")
            }
        )

        Text("2" , style = Typography.headlineMedium)
        Text("육류,생선,달걀,콩,두부 등으로 된 음식을 매일 3회 이상 먹습니까?")
        CustomGroupButtons(
            options = question1 ,
            unSelectedColor = ColorD4D9E1 ,
            selectedColor = Color143F91,
            /*
            icon = Icon(
                imageVector = Icons.Filled.Check,
                modifier = Modifier
                    .padding(8f.pxToDp())
                    .size(37f.pxToDp()),
                contentDescription =  "icon"
            ),*/
            selectionChanged = { it->
                question2Answer = Integer.parseInt(it.toString())
                Log.d("survey" , "answer : $question2Answer")
            }
        )

    }




}
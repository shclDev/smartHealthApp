package com.shcl.smarthealth.presentation.view.survey.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun surveySleep() {


    Column(modifier = Modifier.fillMaxSize()) {
        Text("1" , style = Typography.headlineMedium)
        Text("지난 한달 동안 당신이 취한 전반적인 수면의 질을 어떻게 평가하시겠습니까?")


        Text("2" , style = Typography.headlineMedium)
        Text("지난 한 달 동안, 보통 몇 시에 잠자리에 들고 몇 시에 일어나셨습니까?")

    }



}
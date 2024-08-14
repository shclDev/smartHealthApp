package com.shcl.smarthealth.presentation.view.measurement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun MeasurementScreen(nav : NavHostController, viewModel: MeasurementViewModel = hiltViewModel()){

    Box(
        modifier = Modifier.fillMaxSize()

    ){
        Column(){
            //Text("안녕하세요, 사용자님!" , fontSize = 60f.pxToSp() , fontStyle = Typography.headlineMedium , color = Color1E1E1E)


        }
    }

}
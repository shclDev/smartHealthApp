package com.shcl.smarthealth.presentation.view.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.utils.pxToDp

@Composable
fun AnalysisScreen(nav : NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80f.pxToDp())
            .background(Color.White)

    ){
        Text(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            text = "분석 결과 화면"
        )
    }


}
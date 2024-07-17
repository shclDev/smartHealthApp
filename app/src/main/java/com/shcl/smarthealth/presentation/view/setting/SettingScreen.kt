package com.shcl.smarthealth.presentation.view.setting

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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.utils.pxToDp

@Composable
fun SettingScreen(nav : NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80f.pxToDp())
            .background(Color.White)

    ){
        Text(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            text = "설정 화면",
            textAlign = TextAlign.Start
        )
    }

}
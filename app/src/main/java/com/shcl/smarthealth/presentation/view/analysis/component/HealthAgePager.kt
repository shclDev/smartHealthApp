package com.shcl.smarthealth.presentation.view.analysis.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.shcl.smarthealth.domain.utils.pxToSp

@Composable
fun HealthAgePager(){
    Text("건강나이" , fontSize = 20f.pxToSp())
}
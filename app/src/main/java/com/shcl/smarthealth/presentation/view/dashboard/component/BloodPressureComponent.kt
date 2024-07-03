package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardViewModel


@Composable
fun BloodPressureComponent(viewModel: DashBoardViewModel = hiltViewModel()){

    viewModel.getLastedBloodPressure()
    val bloodPressure by viewModel.bloodPressure.collectAsState()

    Card(
        modifier = Modifier
            .size(width = 554.dp, height = 295.dp)
            .background(color = Color.White)
    ){
        Column {
            Text("수축기/이완기 혈압")
            Text("${bloodPressure?.systolic}/${bloodPressure?.diastolic}  ${bloodPressure?.diastolicUnit}")
        }
    }

}
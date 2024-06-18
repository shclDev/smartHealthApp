package com.shcl.smarthealth.presentation.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DashBoardScreen(nav : NavHostController, viewModel: DashBoardViewModel = hiltViewModel()){

    viewModel.getNutrionAdvice()
    val nutritionAdvice by viewModel.nutritionAdvice.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ){

        Column(
           horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                text = "test"
            )


            nutritionAdvice?.let{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    text = it
                )
            }
        }

    }
}



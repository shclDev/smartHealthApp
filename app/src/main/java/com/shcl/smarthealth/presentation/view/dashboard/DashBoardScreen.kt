package com.shcl.smarthealth.presentation.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.view.dashboard.component.BloodPressureComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.GlucoseComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.PursePressureComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.UserInfo
import com.shcl.smarthealth.presentation.view.dashboard.component.WeightComponent

@Composable
fun DashBoardScreen(nav : NavHostController, viewModel: DashBoardViewModel = hiltViewModel()){

    viewModel.getNutrionAdvice()
    val nutritionAdvice by viewModel.nutritionAdvice.collectAsState()

    viewModel.getLastedBloodPressure()
    val bloodPressure by viewModel.bloodPressure.collectAsState()
    
    viewModel.getLastedWeight()
    val weight by viewModel.bodyComposition.collectAsState()
    

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp)
            .background(Color.White)

    ){

        Column(
           horizontalAlignment = Alignment.Start
        ) {

            UserInfo()
            Spacer(modifier = Modifier.height(53.dp))
            Row(
               horizontalArrangement = Arrangement.spacedBy(30f.pxToDp())
            ) {
                BloodPressureComponent(bloodPressure)
                PursePressureComponent(bloodPressure)
                GlucoseComponent(null )
                WeightComponent(bodyCompositionRoom = weight)
            }

            /*
            nutritionAdvice?.let{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    text = it
                )
            }*/
        }

    }
}



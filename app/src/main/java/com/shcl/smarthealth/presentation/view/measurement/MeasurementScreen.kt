package com.shcl.smarthealth.presentation.view.measurement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.view.measurement.content.AllComplete
import com.shcl.smarthealth.presentation.view.measurement.content.BloodPressureFail
import com.shcl.smarthealth.presentation.view.measurement.content.BloodPressureInit
import com.shcl.smarthealth.presentation.view.measurement.content.BloodPressureSuccess
import com.shcl.smarthealth.presentation.view.measurement.content.BloodPressureWait
import com.shcl.smarthealth.presentation.view.measurement.content.BloodSugarInit
import com.shcl.smarthealth.presentation.view.measurement.content.BloodSugarSuccess
import com.shcl.smarthealth.presentation.view.measurement.content.BloodSugarWait
import com.shcl.smarthealth.presentation.view.measurement.content.Errpage
import com.shcl.smarthealth.presentation.view.measurement.content.HelloScreen
import com.shcl.smarthealth.presentation.view.measurement.content.WatchLoadSuccess
import com.shcl.smarthealth.presentation.view.measurement.content.WatchLoadingScreen
import com.shcl.smarthealth.presentation.view.measurement.content.WeightInit
import com.shcl.smarthealth.presentation.view.measurement.content.WeightSuccess
import com.shcl.smarthealth.presentation.view.measurement.content.WeightWait
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun MeasurementScreen(nav : NavHostController, viewModel: MeasurementViewModel = hiltViewModel()){

    val step by viewModel.measurementStep.collectAsStateWithLifecycle()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 180f.pxToDp(),
                start = 80f.pxToDp(),
                end = 80f.pxToDp(),
                bottom = 120f.pxToDp()
            )
            .background(Color.White)
    ){

       when(step){
           MeasurementStep.HELLO-> HelloScreen(viewModel = viewModel)
           MeasurementStep.WATCH_LOADING-> WatchLoadingScreen(viewModel = viewModel)
           MeasurementStep.WATCH_LOAD_SUCCESS-> WatchLoadSuccess(viewModel = viewModel)
           MeasurementStep.BLOOD_PRESSURE_INIT-> BloodPressureInit(viewModel = viewModel)
           MeasurementStep.BLOOD_PRESSURE_WAIT-> BloodPressureWait(viewModel = viewModel)
           MeasurementStep.BLOOD_PRESSURE_SUCCESS-> BloodPressureSuccess(viewModel = viewModel)
           MeasurementStep.BLOOD_PRESSURE_FAIL-> BloodPressureFail(viewModel = viewModel)
           MeasurementStep.BLOOD_SUGAR_INIT -> BloodSugarInit(viewModel = viewModel)
           MeasurementStep.BLOOD_SUGAR_WAIT-> BloodSugarWait(viewModel = viewModel)
           MeasurementStep.BLOOD_SUGAR_SUCCESS-> BloodSugarSuccess(viewModel = viewModel)
           MeasurementStep.WEIGHT_INIT-> WeightInit(viewModel = viewModel)
           MeasurementStep.WEIGHT_WAIT-> WeightWait(viewModel = viewModel)
           MeasurementStep.WEIGHT_SUCCESS-> WeightSuccess(viewModel = viewModel)
           MeasurementStep.ALL_COMPLTE-> AllComplete(viewModel = viewModel , nav = nav)
           MeasurementStep.ERR_PAGE-> Errpage(viewModel = viewModel)
       }
    }
}

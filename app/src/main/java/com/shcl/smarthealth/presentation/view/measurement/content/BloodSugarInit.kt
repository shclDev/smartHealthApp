package com.shcl.smarthealth.presentation.view.measurement.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.view.measurement.MeasurementViewModel
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun BloodSugarInit(viewModel: MeasurementViewModel){

    val agentLottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.agent))
    val progress by animateLottieCompositionAsState(composition = agentLottie , iterations = LottieConstants.IterateForever)

    val step by viewModel.measurementStep.collectAsStateWithLifecycle()
    val displayTime = step.displayTime * 1000L
    LaunchedEffect(key1 = true) {
        delay(displayTime)
        viewModel.nextStep()
        //nav.navigate(route = OuterScreen.login.route)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceAround) {
        Text(
            text = "${step.title}",
            style = Typography.headlineLarge,
            fontSize = 45f.pxToSp(),
            textAlign = TextAlign.Center,
            color = Color1E1E1E
        )

        Image(
            alignment = Alignment.BottomCenter,
            modifier = Modifier
            .size(178f.pxToDp(), 380f.pxToDp()),
            painter = painterResource(id = R.drawable.agent_bloodsuger),
            contentDescription = null)
    }


}
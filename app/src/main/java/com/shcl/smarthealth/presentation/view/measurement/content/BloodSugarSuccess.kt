package com.shcl.smarthealth.presentation.view.measurement.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomButton
import com.shcl.smarthealth.presentation.view.measurement.MeasurementViewModel
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun BloodSugarSuccess(viewModel: MeasurementViewModel) {

    val step by viewModel.measurementStep.collectAsStateWithLifecycle()
    val title by viewModel.titleText.collectAsStateWithLifecycle()
    val measurement by viewModel.measurementText.collectAsStateWithLifecycle()

    val displayTime = step.displayTime * 1000L
    LaunchedEffect(key1 = true) {
        delay(displayTime)
        viewModel.nextStep()
        //nav.navigate(route = OuterScreen.login.route)
    }
    viewModel.clovaVoice(title)
    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {

        Text(
            modifier = Modifier.weight(0.3f),
            text = "${title}",
            style = Typography.headlineLarge,
            fontSize = 45f.pxToSp(),
            textAlign = TextAlign.Center,
            color = Color1E1E1E
        )


        Text(
            text = "${measurement}",
            style = Typography.headlineLarge,
            fontSize = 300f.pxToSp(),
            textAlign = TextAlign.Center,
            color = Color143F91
        )

        CustomButton(contentColor = Color.White, containerColor = Color143F91 , text = "확인" , buttonWidth = 720f , buttonHeight = 86f , btnClick = {
            viewModel.nextStep()
        } )

    }

}
package com.shcl.smarthealth.presentation.view.measurement.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.view.measurement.MeasurementStep
import com.shcl.smarthealth.presentation.view.measurement.MeasurementViewModel
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD9D9D9
import com.shcl.smarthealth.ui.theme.ColorEBF3FE
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
    viewModel.clovaVoice(step.title)
    viewModel.getDevice("Glucose")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                viewModel.stepJump(MeasurementStep.WEIGHT_INIT)
            }) {
            Text("건너뛰기" , style = Typography.headlineLarge, fontSize = 32f.pxToSp() , fontWeight = FontWeight.W700 , textDecoration = TextDecoration.Underline, color = ColorD9D9D9)
        }
        Text(
            text = "${step.title}",
            style = Typography.headlineLarge,
            fontSize = 45f.pxToSp(),
            textAlign = TextAlign.Center,
            color = Color1E1E1E
        )
        Spacer(modifier = Modifier.height(120f.pxToDp()))
        Box(modifier = Modifier
            .clip(RoundedCornerShape(18f.pxToDp()))
            .fillMaxWidth()
            .background(ColorEBF3FE)
            .padding(top = 71f.pxToDp(), bottom = 71f.pxToDp())) {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(178f.pxToDp(), 380f.pxToDp()).align(Alignment.Center),
                painter = painterResource(id = R.drawable.agent_bloodsuger),
                contentDescription = null
            )
        }
    }


}
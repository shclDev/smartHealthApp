package com.shcl.smarthealth.presentation.view.reservation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimatable
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp

@Composable
fun ReservationScreen(nav : NavHostController){
    
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.agent))
    val composition_bell by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.bell))
    val progress by animateLottieCompositionAsState(composition = composition , iterations = LottieConstants.IterateForever)



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(80f.pxToDp())
            .background(Color.White)

    ){


        LottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .size(600f.pxToDp()),
            composition = composition,
            progress = { progress},
        )

        /*
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            text = "예약 화면",
            textAlign = TextAlign.Start
        )*/
    }

}
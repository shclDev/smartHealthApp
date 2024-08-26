package com.shcl.smarthealth.presentation.view.introduce

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.transition.Visibility
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.view.survey.SurveyViewModel
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Typography




@Composable
fun IntroduceScreen(nav : NavHostController , viewModel: IntroduceViewModel = hiltViewModel()){

    val micVisible by viewModel.micVisible.collectAsStateWithLifecycle()
    val voiceMessage by viewModel.voiceMessage.collectAsStateWithLifecycle()
    val recognizerMessage by viewModel.recognizeMessage.collectAsStateWithLifecycle()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(brush = Brush.verticalGradient(BackGroundColor))
            .fillMaxSize()
            .padding(
                top = 180f.pxToDp(),
                start = 80f.pxToDp(),
                end = 80f.pxToDp(),
                bottom = 120f.pxToDp()
            )
    ){
        Column(modifier = Modifier.fillMaxSize() , verticalArrangement = Arrangement.SpaceAround) {
            Text(
                modifier = Modifier.weight(0.3f),
                text = voiceMessage,
                style = Typography.headlineLarge,
                fontSize = 45f.pxToSp(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            AnimatedVisibility(
                visible = micVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                exit = fadeOut(animationSpec = tween(durationMillis = 500))
            ){
                Image(
                    modifier = Modifier.size(382f.pxToDp()).clickable {
                        viewModel.speech()
                    },
                    painter = painterResource(id = R.drawable.mic),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.weight(0.3f),
                text = recognizerMessage,
                style = Typography.headlineLarge,
                fontSize = 45f.pxToSp(),
                textAlign = TextAlign.Center,
                color = Color.White
            )

        }

        /*
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.vui_state),
            contentDescription = null
        )*/
    }

}
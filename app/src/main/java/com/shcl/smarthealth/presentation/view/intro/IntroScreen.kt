package com.shcl.smarthealth.presentation.view.intro

import android.os.Build
import android.transition.Transition
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.Utils.dp
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.featureThatRequiresPermission
import com.shcl.smarthealth.ui.theme.BackGroundColor
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

enum class MoveState  { Move, Stop  }

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun IntroScreen(nav : NavHostController , modifier: Modifier?){

    //requestPermission()
    //val context = LocalContext.current

    var isVisible by remember { mutableStateOf(true) }
    var fullIsVisible by remember { mutableStateOf(true) }

    var allPermissionGranted by remember { mutableStateOf( false ) }
    featureThatRequiresPermission( { permissionGranted ->
        allPermissionGranted = permissionGranted
        if(allPermissionGranted){
            isVisible = true
            fullIsVisible  =true
            nextScreen(nav = nav)
        }
    })

    /*
    LaunchedEffect(key1 = true) {
        delay(1500L)
        nav.navigate(route = OuterScreen.login.route)
    }*/



    AnimatedVisibility(
        visible = fullIsVisible,
        enter = fadeIn() + slideInHorizontally(
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        ),
        exit = fadeOut() + slideOutHorizontally(
            animationSpec = tween(durationMillis = 1500, easing = FastOutLinearInEasing)
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(BackGroundColor))
            //.gradientBackground(BackGroundColor, angle = 90f)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + slideInHorizontally(
                        animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
                    ),
                    exit = fadeOut() + slideOutHorizontally(
                        animationSpec = tween(durationMillis = 1500, easing = FastOutLinearInEasing)
                    ),
                ) {
                    Box {
                        Image(
                            modifier = Modifier.size(497.pxToDp(), 278.pxToDp()),
                            painter = painterResource(id = R.drawable.logo_main),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.pxToDp()))

                /*
                Button(
                    onClick = {
                        //FeatureThatRequiresCameraPermission()
                        //nav.navigate(route = Screen.dashboard.route)
                        //nav.navigate(route = Screen.Login.route)
                        nav.navigate(route = Screen.login.route)
                    }
                ){
                    Text("go to dashboard")
                }*/
                //requestPermission()
                //featureThatRequiresCameraPermission()

            }

        }
    }
}



@Composable
fun nextScreen(nav: NavHostController){
    LaunchedEffect(key1 = true) {
            delay(1500L)
            nav.navigate(route = OuterScreen.login.route)
    }
}


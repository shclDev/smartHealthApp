package com.shcl.smarthealth.presentation.view.intro

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.featureThatRequiresPermission
import com.shcl.smarthealth.ui.theme.BackGroundColor
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun IntroScreen(nav : NavHostController , modifier: Modifier?){

    //requestPermission()
    //val context = LocalContext.current

    val transitionState = remember { MutableTransitionState(false)}
    var allPermissionGranted by remember { mutableStateOf( false ) }
    featureThatRequiresPermission( { permissionGranted ->
        allPermissionGranted = permissionGranted
        /*
        if(allPermissionGranted){
            nextScreen(nav = nav)
        }*/
        if(allPermissionGranted){
            LaunchedEffect(key1 = true) {
                transitionState.targetState = true
                delay(1500L)
                nav.navigate(route = OuterScreen.login.route)
            }
        }
    })

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val screenWidthPx = (screenWidthDp * density).toInt()
    val screenHeightPx = (screenHeightDp * density).toInt()


    val aniWidth by animateDpAsState(targetValue = if(transitionState.currentState) (screenWidthPx/2).pxToDp() else screenWidthPx.pxToDp() ,
            animationSpec = tween(durationMillis = 3000 , delayMillis = 700)
    )

    LaunchedEffect(Unit) {
        transitionState.targetState = true
     }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(width = aniWidth, height = screenHeightPx.pxToDp())
                .fillMaxHeight()
                .background(brush = Brush.verticalGradient(BackGroundColor))
            //.gradientBackground(BackGroundColor, angle = 90f)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box {
                    Image(
                        modifier = Modifier.size(497.pxToDp(), 278.pxToDp()),
                        painter = painterResource(id = R.drawable.logo_main),
                        contentDescription = null
                    )
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


fun Modifier.gradientBackground(colors : List<Color>, angle : Float ) = this.then(
    Modifier.drawBehind {

        // Setting the angle in radians
        val angleRad = angle / 180f * PI

        // Fractional x
        val x = cos(angleRad).toFloat()

        // Fractional y
        val y = sin(angleRad).toFloat()

        // Set the Radius and offset as shown below
        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        // Setting the exact offset
        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        // Draw a rectangle with the above values
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)

@Composable
fun nextScreen(nav: NavHostController){
    LaunchedEffect(key1 = true) {
            delay(1500L)
            nav.navigate(route = OuterScreen.login.route)
    }
}

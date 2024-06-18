package com.shcl.smarthealth.presentation.view.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.presentation.navigation.Screen


@Composable
fun IntroScreen(nav : NavHostController , modifier: Modifier?){

    val logo = painterResource(id = R.drawable.intro_logo)

    Card(
      modifier = Modifier
          .fillMaxSize()
          .background(Color.Blue)
    //.gradientBackground(BackGroundColor, angle = 90f)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ){
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.intro_desc1),
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Box {
                    Image(
                        modifier = Modifier.size(375.dp, 131.dp),
                        painter = logo,
                        contentDescription = null
                    )
                }
                Text(
                    text = stringResource(id = R.string.intro_desc2),
                    color = Color.White,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp) )

                Button(
                    onClick = {
                        nav.navigate(route = Screen.dashboard.route)
                    }
                ){
                    Text("go to dashboard")
                }

            }

        }
    }

}


/*
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
*/
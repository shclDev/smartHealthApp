package com.shcl.smarthealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.SmartHealthTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import jp.co.ohq.ble.OHQDeviceManager;

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartHealthTheme {
                    MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Box(
       modifier = Modifier
           .fillMaxSize()
           .gradientBackground(BackGroundColor, angle = 90f)
    ){
        Row(modifier = Modifier.padding(12.dp)){
            Column(modifier = modifier.padding(24.dp)) {
                Text("Hello")
                Text("Android")
            }
            Column(modifier = modifier.padding(24.dp)) {
                Text("Hello")
                Text("Android")
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(if(expanded) "show less" else "show more")
            }
        }

    }
}

@Composable
fun OnBoardingScreen(
    onContinuseClicked : () -> Unit,
    modifier : Modifier = Modifier
){
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcom to BBasis")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinuseClicked
        ){
            Text("continue")
        }
    }

}

@Composable
fun MyApp(modifier: Modifier){
    var shouldShowOnboarding by remember { mutableStateOf(true)}

    Surface(modifier){
        if(shouldShowOnboarding){
            OnBoardingScreen(onContinuseClicked = { shouldShowOnboarding = false})
        }else{
            Greeting(name = "sdafd");
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

@Preview(showBackground = true , widthDp = 300 , heightDp = 300)
@Composable
fun GreetingPreview() {
    SmartHealthTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true , widthDp = 300 , heightDp = 300)
@Composable
fun SmartHealthPreview() {
    SmartHealthTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
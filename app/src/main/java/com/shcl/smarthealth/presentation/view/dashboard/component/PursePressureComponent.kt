package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF1F4F9
import com.shcl.smarthealth.ui.theme.Typography
import kotlin.math.roundToInt

@Composable
fun PursePressureComponent(bloodPressure : BloodPressureRoom?){

    Box(
        modifier = Modifier
            .background(color = ColorF1F4F9)
            .defaultMinSize(minWidth = 288f.pxToDp(), minHeight = 360f.pxToDp())
            .height(415f.pxToDp())
            .border(width = 1.dp, color = ColorD4D9E1, shape = RoundedCornerShape(18.dp))
            .padding(30.dp)
    ){
        Column(modifier = Modifier
            .align(Alignment.Center)
           , verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier =
                Modifier
                    .size(120.pxToDp(), 120.pxToDp())
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.purse_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(15f.pxToDp()))

            Text("맥박" , style = Typography.headlineMedium , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 20f.sp)

            Spacer(modifier = Modifier.height(15f.pxToDp()))

            bloodPressure?.let{
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${it.pulseRate.roundToInt()}" , style = Typography.labelSmall , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 20f.sp)
                    Text("${it.systolicUnit}", style = Typography.labelSmall , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 15f.sp)
                }
            } ?:Row(verticalAlignment = Alignment.CenterVertically) {
                Text("-" , style = Typography.labelSmall , color = Color1E1E1E, textAlign = TextAlign.Center,fontSize = 20f.sp)
                Text("mmHg", style = Typography.labelSmall , color = Color1E1E1E, textAlign = TextAlign.Center,fontSize = 15f.sp)
            }

            Text("그래프 영역",fontSize = 12f.sp)

        }
    }


}
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD49E1
import com.shcl.smarthealth.ui.theme.Typography
import kotlin.math.roundToInt

@Composable
fun PursePressureComponent(bloodPressure : BloodPressureRoom?){

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .size(width = 288f.pxToDp(), height = 600f.pxToDp())
            //.defaultMinSize(minWidth = 288f.pxToDp(), minHeight = 360f.pxToDp())
            .border(width = 1.dp, color = ColorD49E1, shape = RoundedCornerShape(18.dp))
            .padding(30.dp)
    ){
        Column(modifier = Modifier
            .background(Color.White)
            .align(Alignment.Center)
           , verticalArrangement = Arrangement.SpaceAround
        ) {

            Image(
                modifier =
                Modifier.size(72f.pxToDp(), 39f.pxToDp()).align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.purse_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(20f.pxToDp()))

            Text("맥압" , style = Typography.headlineMedium , color = Color1E1E1E)

            Spacer(modifier = Modifier.width(20f.pxToDp()))

            Row {
                Text("${bloodPressure?.pulseRate?.roundToInt()}" , style = Typography.labelSmall , color = Color1E1E1E)
                Text("${bloodPressure?.systolicUnit}", style = Typography.labelSmall , color = Color1E1E1E)
            }
        }
    }


}
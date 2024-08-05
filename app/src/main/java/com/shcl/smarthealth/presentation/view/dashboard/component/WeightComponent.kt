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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF1F4F9
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun WeightComponent(bodyCompositionRoom: BodyCompositionRoom?){

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(18f.pxToDp()))
            .background(color = ColorF1F4F9)
            .border(width = 1.dp, color = ColorD4D9E1 , shape = RoundedCornerShape(18f.pxToDp()))
            .defaultMinSize(minWidth = 288f.pxToDp(), minHeight = 360f.pxToDp())
            .height(415f.pxToDp())
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
                painter = painterResource(id = R.drawable.weight_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(15f.pxToDp()))

            Text("몸무게" , style = Typography.headlineMedium , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 20f.sp)

            Spacer(modifier = Modifier.height(15f.pxToDp()))

            bodyCompositionRoom?.let{
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${it.weight}" , style = Typography.bodyLarge , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 20f.sp)
                    Text("${it.weightUnit}", style = Typography.bodyLarge , color = Color1E1E1E , textAlign = TextAlign.Center,fontSize = 15f.sp)
                }
            } ?: Row(verticalAlignment = Alignment.CenterVertically) {
                Text("-" , style = Typography.bodyLarge , color = Color1E1E1E, textAlign = TextAlign.Center,fontSize = 20f.sp)
                Text("kg", style = Typography.bodyLarge , color = Color1E1E1E, textAlign = TextAlign.Center,fontSize = 15f.sp)
            }

            Text("어제 대비",fontSize = 12f.sp)

        }
    }



}
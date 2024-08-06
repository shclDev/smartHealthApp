package com.shcl.smarthealth.presentation.view.survey.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.StepBackGroundColor
import com.shcl.smarthealth.ui.theme.Typography
import com.shcl.smarthealth.ui.theme.stepTextColor

@Composable
fun Step(level : Int){

    val textMeasurer  = rememberTextMeasurer()

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W500,
                brush = Brush.verticalGradient(colors = stepTextColor)
            )
        ){
            append("$level")
        }
    }

    Row(){

        if(level > 1){
            Spacer(
                modifier = Modifier
                    .height(1f.pxToDp())
                    .width(50f.pxToDp())
                    .align(Alignment.CenterVertically)
                    .background(color = Color143F91)
            )
        }

        Canvas(
            modifier =
            Modifier
                .size(55f.pxToDp()),
            onDraw = {
                drawText(textMeasurer , annotatedText)
                drawCircle(
                    color = Color143F91 , radius = 25f)

            }

        )
    }
}
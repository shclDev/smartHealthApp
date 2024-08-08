package com.shcl.smarthealth.presentation.view.survey.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.StepBackGroundColor
import com.shcl.smarthealth.ui.theme.Typography
import com.shcl.smarthealth.ui.theme.stepTextColor

@Composable
fun Step(currentLevel : Int , maxLevel : Int){

    val textMeasurer  = rememberTextMeasurer()

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W500,
                color = Color.White
            )
        ){
            append("$currentLevel")
        }
    }

    Row(){

        for(step in 1..maxLevel){
            if(step > 1){
                if(currentLevel < step){
                    Spacer(
                        modifier = Modifier
                            .height(1f.pxToDp())
                            .width(50f.pxToDp())
                            .align(Alignment.CenterVertically)
                            .background(color = ColorD4D9E1)
                    )
                }else{
                    Spacer(
                        modifier = Modifier
                            .height(1f.pxToDp())
                            .width(50f.pxToDp())
                            .align(Alignment.CenterVertically)
                            .background(color = Color143F91)
                    )
                }
            }


            if(currentLevel < step){
                TextButton(
                    modifier = Modifier
                        .size(width = 55f.pxToDp(), height = 55f.pxToDp())
                        .clip(CircleShape)
                        .border(width = 1f.pxToDp(), color = ColorD4D9E1, shape = CircleShape)
                       ,
                    colors =ButtonColors(containerColor = Color.Transparent, contentColor = ColorD4D9E1, disabledContentColor =Color.Transparent , disabledContainerColor =Color.Transparent),
                    shape = CircleShape,
                    onClick = { /*TODO*/ }) {
                    Text("$step" , color = ColorD4D9E1 , fontSize = 15f.pxToSp())

                }
            }else{
                TextButton(
                    modifier = Modifier
                        .size(width = 55f.pxToDp(), height = 55f.pxToDp())
                        .clip(CircleShape),
                        //.border(width = 10f.pxToDp(), color = Color.Transparent, shape = CircleShape),
                    colors = ButtonColors(containerColor = Color143F91, contentColor = Color.White, disabledContentColor =Color143F91 , disabledContainerColor =Color143F91),
                    shape = CircleShape,
                    onClick = { /*TODO*/ }
                ) {
                    Text("$step", color = Color.White , fontSize = 15f.pxToSp())
                }
            }

        }

    }
}
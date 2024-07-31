package com.shcl.smarthealth.presentation.ui.common

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun CustomCheckBox(options : HashMap<String , Any>,
                   checkboxSize : Float = 12f,
                   unSelectedColor : Color,
                   selectedColor : Color,
                   initSelect : String? =  null,
                   selectionChanged : (String)-> Unit
) {

    var selectedOption by remember { mutableStateOf("") }
    val onSelectionChange = { text : String->{
        selectedOption  = text
        Log.d("smartHealth" , selectedOption)
    }}

    if(!initSelect.isNullOrEmpty()){
        //selectedOption = initSelect
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(20f.pxToDp()),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            Row(modifier = Modifier.padding(all = 8.pxToDp()).clickable { selectedOption = option.key
                selectionChanged(option.value as String)
            }) {
                Canvas(
                    modifier = Modifier
                        .size(37f.pxToDp())
                        .clip(CircleShape)
                        .background(
                            if(selectedOption == option.key){
                                selectedColor
                            }else{
                                unSelectedColor
                            }),
                    onDraw = {
                        //val strokeWidth = (size / 2)

                        drawCircle(
                            color = Color.White,
                            radius = 12f,
                            style = Stroke(width = 8f),
                            center = this.center
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10f.pxToDp()))
                Text(
                    text = option.key,
                    textAlign = TextAlign.Center,
                    style = Typography.titleSmall,
                    color = Color333333,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)


                )
            }
        }
    }
}

@Composable
fun DrawCircle(color : Color , size : Float){

    Canvas(
        modifier = Modifier
            .size(37f.pxToDp())
            .clip(CircleShape)
            .background(color),
        onDraw = {
            val strokeWidth = (size / 2)

            drawCircle(
                color = Color.White,
                radius = 12f,
                style = Stroke(width = 8f),
                center = this.center
            )
        }
    )

}
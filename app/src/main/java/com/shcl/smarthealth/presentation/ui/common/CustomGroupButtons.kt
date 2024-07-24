package com.shcl.smarthealth.presentation.ui.common

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF1F3F8
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun CustomGroupButtons(
    options : HashMap<String , Any>,
    unSelectedColor : Color,
    selectedColor : Color,
    selectionChanged : ()-> Unit
    ) {

    var selectedOption by remember { mutableStateOf("") }
    val onSelectionChange = { text : String->{
        selectedOption  = text
        Log.d("smartHealth" , selectedOption)
    }}

    var buttonBackColor by remember { mutableStateOf(unSelectedColor) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(20f.pxToDp()),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            Row(modifier = Modifier.padding(all = 8.pxToDp())) {
                Text(
                    text = option.key,
                    textAlign = TextAlign.Center,
                    style = Typography.titleSmall,
                    color = Color333333,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(18f.pxToDp()))
                        .background(ColorD4D9E1)
                        .defaultMinSize(minWidth = 300f.pxToDp(), 86f.pxToDp()).align(Alignment.CenterVertically)
                        .clickable {
                            //selectedOption = option.key
                            onSelectionChange(option.key)
                            if (option.key == selectedOption) {
                                buttonBackColor = selectedColor
                            }else{
                                buttonBackColor = unSelectedColor
                            }
                        }
                        .background(
                            if(selectedOption == option.key){
                                Color.Magenta
                            }else{
                                Color.LightGray
                            }
                        )
                        .border(
                            width = 3f.pxToDp(),
                            color = buttonBackColor
                        )
                        .padding(
                            vertical = 12f.pxToDp(),
                            horizontal = 16f.pxToDp()
                        )
                )
            }
        }
    }
}
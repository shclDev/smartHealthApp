package com.shcl.smarthealth.presentation.ui.common

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF1F3F8
import com.shcl.smarthealth.ui.theme.Typography
import java.util.Collections
import java.util.stream.Collectors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomGroupButtons(
    reverseSort : Boolean = false,
    options : HashMap<String , Any>,
    unSelectedColor : Color,
    selectedColor : Color,
    containerColor : Color? = null,
    icon : ImageVector? = null,
    selectionChanged : (Any)-> Unit
    ) {


    var selectedOption by remember { mutableStateOf("") }
    //var buttonBoardColor by remember { mutableStateOf(unSelectedColor) }
    //val reverseMap = options.entries.stream().map(item -> item.getKey()).sorted().collect(Collectors.toList())

    FlowRow(
        modifier = Modifier.padding(top = 40f.pxToDp() , bottom = 80f.pxToDp()),
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(5f.pxToDp()),
        //modifier = Modifier.padding(top = 29f.pxToDp())
    ) {

        options.toSortedMap().forEach { option ->
            Row(modifier = Modifier.padding(all = 8.pxToDp())) {
                OutlinedButton(
                    shape = RoundedCornerShape(18f.pxToDp()),
                    border = BorderStroke(width = 3f.pxToDp() , color = if (selectedOption == option.value.toString()) {
                        selectedColor
                    } else {
                        unSelectedColor
                    }),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor ?: ColorD4D9E1),
                    modifier = Modifier
                        .defaultMinSize(minWidth = 250f.pxToDp(), 86f.pxToDp())
                        .align(Alignment.CenterVertically),
                    onClick = {
                    selectedOption = option.value.toString()
                    selectionChanged(option.value)
                }) {
                    Text(
                        text = option.key,
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        color = Color333333,
                    )

                    Spacer(modifier = Modifier.width(15f.pxToDp()))

                    icon?.let {
                        if(selectedOption == option.value.toString())
                            Icon(imageVector = icon, contentDescription = "")
                    }
                }
            }
        }
    }
}
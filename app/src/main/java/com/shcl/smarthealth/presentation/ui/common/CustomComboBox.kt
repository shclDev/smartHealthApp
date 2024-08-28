package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.min
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color757575

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomComboBox(
    subject : String?,
    firstUnit : String="",
    secondUnit : String="",
    list : ArrayList<String>,
    selected : (Any)->Unit
){

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("| 선택") }

    Row(modifier = Modifier.padding(start = 32f.pxToDp()), verticalAlignment = Alignment.CenterVertically) {

        Text("${subject}",fontSize = 22f.pxToSp())

        Spacer(modifier = Modifier.width(50f.pxToDp()))

        Box(
            modifier = Modifier
                .clickable { expanded = true }
                .background(Color.White)
                .clip(RoundedCornerShape(18f.pxToDp()))
                .defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 85f.pxToDp())
                .border(
                    width = 2f.pxToDp(),
                    color = Color757575
                )

                .padding(25f.pxToDp())

        ) {
            Text("${selected}" , color = Color1E1E1E , fontSize = 20f.pxToSp())

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                list.forEachIndexed { index, label ->
                    DropdownMenuItem(modifier = Modifier
                        .background(Color.White)
                        .border(width = 1f.pxToDp(), color = Color.Gray)
                        , text = { Text("$label" , fontSize = 22f.pxToSp()) }, onClick = {
                        expanded = false
                        selected = label
                        selected(list[index]) })
                }
            }
        }
        Spacer(modifier = Modifier.width(10f.pxToDp()))
        Text("${firstUnit}",fontSize = 22f.pxToSp())
    }


}
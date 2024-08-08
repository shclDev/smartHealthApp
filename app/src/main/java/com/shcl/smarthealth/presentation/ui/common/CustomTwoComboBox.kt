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
import androidx.compose.foundation.layout.height
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
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTwoComboBox(
    subject : String?,
    firstList : MutableList<String>,
    firstUnit : String = "",
    secondUnit : String = "",
    secondList : MutableList<String>,
    firstSelected : ()->Unit,
    secondSelected : ()->Unit
) {

    var firstExpanded by remember { mutableStateOf(false ) }
    var firstSelected by remember { mutableStateOf("| 선택") }

    var secondExpanded by remember { mutableStateOf(false ) }
    var secondSelected by remember { mutableStateOf("| 선택") }

    Row(modifier = Modifier
        .padding(32f.pxToDp())
       , verticalAlignment = Alignment.CenterVertically) {

        Text("${subject}" , fontSize = 22f.pxToSp())

        Spacer(modifier = Modifier.width(50f.pxToDp()))

        Box {
            Row(
                modifier = Modifier
                    .clickable { firstExpanded = true }
                    .defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 85f.pxToDp())
                    .background(Color.White)
                    .clip(RoundedCornerShape(18f.pxToDp()))
                    .border(
                        width = 2f.pxToDp(),
                        color = Color757575
                    )
                    .padding(25f.pxToDp())
            ){
                Text( text = "${firstSelected}" , color = Color757575 , fontSize = 22f.pxToSp() )
            }
            DropdownMenu(expanded = firstExpanded,
                onDismissRequest = { firstExpanded = false }
            ) {

                firstList.forEachIndexed { index, label ->
                    DropdownMenuItem(text = { Text("$label" , fontSize = 22f.pxToSp())  }, onClick = {
                        firstExpanded = false
                        firstSelected = label
                        //firstSelected()
                    })
                }

            }
        }
        Spacer(modifier = Modifier.width(30f.pxToDp()))
        Text("$firstUnit" , fontSize = 22f.pxToSp())
        Spacer(modifier = Modifier.width(30f.pxToDp()))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32f.pxToDp())

        ) {
            Row(
                modifier = Modifier
                    .clickable { secondExpanded = true }
                    .defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 85f.pxToDp())
                    .background(Color.White)
                    .clip(RoundedCornerShape(18f.pxToDp()))
                    .border(
                        width = 2f.pxToDp(),
                        color = Color757575
                    )
                    .padding(25f.pxToDp())
            ){
                Text( text = "${secondSelected}" , color = Color757575 ,fontSize = 22f.pxToSp() )
            }
            DropdownMenu(expanded = secondExpanded,
                onDismissRequest = { secondExpanded = false }
            ) {

                secondList.forEachIndexed { index, label ->
                    DropdownMenuItem(text = { Text("$label" , fontSize = 22f.pxToSp()) }, onClick = {
                        secondExpanded = false
                        secondSelected = label
                        //secondSelected()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.width(30f.pxToDp()))
        Text("$secondUnit" , fontSize = 22f.pxToSp())
    }

}

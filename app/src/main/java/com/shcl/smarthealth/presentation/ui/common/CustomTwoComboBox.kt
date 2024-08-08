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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
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

    Row(modifier = Modifier.fillMaxWidth()
        .padding(top = 32f.pxToDp())
       , verticalAlignment = Alignment.CenterVertically) {

        Text("${subject}" , fontSize = 22f.pxToSp())

        Spacer(modifier = Modifier.width(50f.pxToDp()))

        Box() {
            Row(
                modifier = Modifier
                    .clickable { firstExpanded = true }
                    .border(
                        width = 2f.pxToDp(),
                        color = Color757575,
                        shape = RoundedCornerShape(18f.pxToDp())
                    )
                    .defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 85f.pxToDp())
                    .background(Color.White)
                    .padding(15f.pxToDp())
            ){
                Text( text = "${firstSelected}" , color = Color757575 , fontSize = 20f.pxToSp() )
            }
            DropdownMenu(expanded = firstExpanded,
                modifier = Modifier.background(Color.White),
                onDismissRequest = { firstExpanded = false }
            ) {

                firstList.forEachIndexed { index, label ->
                    DropdownMenuItem( modifier = Modifier
                        .background(Color.White)
                        .border(width = 1f.pxToDp(), color = Color.Gray),text = { Text("$label" , fontSize = 20f.pxToSp())  },
                        onClick = {
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
                    .border(
                        width = 2f.pxToDp(),
                        color = Color757575,
                        shape = RoundedCornerShape(18f.pxToDp())
                    )
                    .padding(15f.pxToDp())
            ){
                Text( secondSelected , color = Color757575 ,fontSize = 20f.pxToSp() )
            }
            DropdownMenu(
                expanded = secondExpanded,
                modifier = Modifier.background(Color.White),
                onDismissRequest = { secondExpanded = false }
            ) {

                secondList.forEachIndexed { index, label ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(Color.White)
                            .border(width = 1f.pxToDp(), color = Color.LightGray),
                        text = { Text("$label" , fontSize = 20f.pxToSp() , textAlign = TextAlign.Center) }, onClick = {
                        secondExpanded = false
                        secondSelected = label
                        //secondSelected()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.width(30f.pxToDp()))
        Text(secondUnit, fontSize = 22f.pxToSp())
    }

}

package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp


@Composable
fun CustomRadioButton(
    options : HashMap<String , Any>,
    selectionChanged : (Any)-> Unit
) {

    var selectedObserver by remember { mutableStateOf("") }

    Row(){
            options.forEach {
                Row(modifier = Modifier
                    .selectable(
                        selected = (it.key == selectedObserver),
                        onClick = {
                            selectedObserver = it.key
                            selectionChanged(it.value)
                        }
                    ).align(Alignment.CenterVertically)

                ) {
                    RadioButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        selected = (it.key == selectedObserver), onClick = {
                        selectedObserver = it.key
                        selectionChanged(it.value) })
                    Text(modifier = Modifier.padding(start = 16f.pxToDp()),
                        text = "${it.key}", fontSize = 16f.pxToSp(), textAlign = TextAlign.Start)
                }
                //Spacer(modifier = Modifier.width(50f.pxToDp()))
            }

        }
}
package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import com.shcl.smarthealth.domain.utils.pxToDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomComboBox(
    subject : String?,
    list : ArrayList<String>,
    selected : ()->Unit
){

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(list[0]) }

    Row {

        Text("${subject}")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32f.pxToDp())

        ) {
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                for (i in 0..list.size) {
                    DropdownMenuItem(text = { Text("${list[i]}") }, onClick = { selected() })
                }
            }
        }
    }


}
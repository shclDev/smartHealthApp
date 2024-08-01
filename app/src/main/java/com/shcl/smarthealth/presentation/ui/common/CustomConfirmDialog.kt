package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun CustomConfirmDialog(
    show : Boolean,
    title : String,
    desc : String,
    onDismiss : ()-> Unit,
    onConfirm : ()-> Unit

){

    if(show){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton ={
                TextButton(onClick = onConfirm) {
                    Text("확인")
                }
            },
            title = { Text(text = title , style = Typography.titleMedium , fontSize = 25f.pxToSp())   },
            text = { Text(text = desc , style = Typography.titleSmall , fontSize = 20f.pxToSp() ) }
        )
    }

}
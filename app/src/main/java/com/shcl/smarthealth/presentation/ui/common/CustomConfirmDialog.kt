package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color4C96FF
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorFFFFF
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
            modifier = Modifier
                .defaultMinSize(minHeight = 505f.pxToDp(), minWidth = 800f.pxToDp())
                .background(ColorFFFFF)
                .padding(
                    start = 40f.pxToDp(),
                    top = 105f.pxToDp(),
                    bottom = 40f.pxToDp(),
                    end = 40f.pxToDp()
                ),
            onDismissRequest = onDismiss,
            confirmButton ={
                TextButton(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(18f.pxToDp()))
                        .background(Color4C96FF)

                        .fillMaxWidth(),
                    onClick = onConfirm) {
                    Text("확인",  style = Typography.bodySmall , fontSize = 25f.pxToSp() , color = Color.White )
                }
            },
            title = { Text(text = title , style = Typography.titleMedium , fontSize = 40f.pxToSp() , color = Color1E1E1E)   },
            text = { Text(text = desc , style = Typography.titleSmall , fontSize = 20f.pxToSp() , color = Color757575) }
        )
    }

}
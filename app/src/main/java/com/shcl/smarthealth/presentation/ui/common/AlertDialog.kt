package com.shcl.smarthealth.presentation.ui.common

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shcl.smarthealth.domain.utils.Utils.dp
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun AlertDialog(
    title : String ,
    desc : String,
    onClickCancel : ()-> Unit,
    onClickConfirm : () -> Unit
    ) {

    Dialog(
        onDismissRequest = { onClickCancel()},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
            )
        ){
            Card(
               shape = RoundedCornerShape(30f.pxToDp())
            ){
                Column(
                    modifier = Modifier
                        .size(width = 800f.pxToDp(), height = 450f.pxToDp())
                        .wrapContentHeight()
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(105f.pxToDp()))

                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        style = Typography.headlineMedium,
                        fontSize = 25f.pxToSp()
                    )

                    Spacer(modifier = Modifier.height(30f.pxToDp()))

                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        style = Typography.labelSmall,
                        fontSize = 15f.pxToSp()
                    )

                    Spacer(modifier = Modifier.height(60f.pxToDp()))
                    
                    Button(
                        //modifier =Modifier.background(0) ,
                        shape = RoundedCornerShape(18f.pxToDp()),
                        onClick = {  }) {

                        Text("확인" , fontSize = 15f.pxToSp(), color = Color.White)
                        
                    }

                }



            }

        }


    )

}
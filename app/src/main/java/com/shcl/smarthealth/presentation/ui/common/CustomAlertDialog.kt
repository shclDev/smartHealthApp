package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorECF098
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun CustomAlertDialog(
    title : String,
    desc : String,
    icon : ImageVector,
    onClickCancel : ()-> Unit,
    onClickConfirm : () -> Unit
    ) {

    Dialog(
        onDismissRequest = { onClickCancel()},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
        ){
            Card(
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 46f.pxToDp(), vertical = 41f.pxToDp())
                    .defaultMinSize(minWidth = 450f.pxToDp(), minHeight = 920f.pxToDp()),
               shape = RoundedCornerShape(30f.pxToDp())
            ){
                Column(
                    modifier = Modifier
                        .wrapContentHeight(),
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
                        text = desc,
                        textAlign = TextAlign.Start,
                        style = Typography.labelSmall,
                        fontSize = 15f.pxToSp()
                    )

                    Spacer(modifier = Modifier.height(60f.pxToDp()))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 86f.pxToDp())
                    ) {

                        Button(
                            modifier = Modifier
                                .background(ColorECF098)
                                .fillMaxWidth(0.5f),
                            //modifier =Modifier.background(0) ,
                            shape = RoundedCornerShape(18f.pxToDp()),
                            onClick = { onClickConfirm() }) {

                            Text("확인", fontSize = 15f.pxToSp(), color = Color757575)

                        }
                        Spacer(modifier = Modifier.width(20f.pxToDp()))
                        Button(
                            modifier = Modifier
                                .background(ColorECF098)
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(18f.pxToDp()),
                            onClick = { onClickConfirm() }) {

                            Text("완료", fontSize = 15f.pxToSp(), color = Color.White)

                        }
                    }
                }

            }

        }
}
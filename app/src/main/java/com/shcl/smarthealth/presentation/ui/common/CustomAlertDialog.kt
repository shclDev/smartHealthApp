package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    onShowDialog : (Boolean) -> Unit,
    icon : ImageVector? = null,
    onClickCancel : ()-> Unit,
    onClickConfirm : () -> Unit
    ) {

    Dialog(
        onDismissRequest = { onShowDialog(false)},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
        ){
            Surface(
                modifier = Modifier
                    .background(Color.White)
                    .padding(
                        start = 40f.pxToDp(),
                        end = 43f.pxToDp(),
                        top = 105f.pxToDp(),
                        bottom = 41f.pxToDp()
                    )
                    .defaultMinSize(minWidth = 920f.pxToDp(), minHeight = 450f.pxToDp())
                    .wrapContentHeight(),
                shape = RoundedCornerShape(30f.pxToDp()),
            ){
                Column(
                    modifier = Modifier.background(Color.White),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        style = Typography.headlineMedium,
                        fontSize = 30f.pxToSp()
                    )

                    Spacer(modifier = Modifier.height(50f.pxToDp()))

                    Text(
                        text = desc,
                        textAlign = TextAlign.Start,
                        style = Typography.labelSmall,
                        fontSize = 20f.pxToSp()
                    )

                    Spacer(modifier = Modifier.height(60f.pxToDp()))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
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
                            onClick = { onClickConfirm()
                                onShowDialog(false)
                            }) {

                            Text("확인", fontSize = 15f.pxToSp(), color = Color757575)

                        }
                        Button(
                            modifier = Modifier
                                .background(ColorECF098)
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(18f.pxToDp()),
                            onClick = { onClickConfirm()
                                onShowDialog(false)
                            }) {
                            Text("완료", fontSize = 15f.pxToSp(), color = Color.White)
                        }
                    }
                }

            }

        }
}
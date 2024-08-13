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
import androidx.compose.material3.TextButton
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
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color4C96FF
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.Color94918A
import com.shcl.smarthealth.ui.theme.ColorECF098
import com.shcl.smarthealth.ui.theme.ColorECF0F8
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun CustomAlertDialog(
    title : String,
    desc : String,
    onShowDialog :Boolean,
    icon : ImageVector? = null,
    onClickCancel : ()-> Unit,
    onClickConfirm : () -> Unit
    ) {

    if(onShowDialog){
        Dialog(
            onDismissRequest = { onClickCancel },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true),
        ){
            Surface(
                shape = RoundedCornerShape(18f.pxToDp()),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(18f.pxToDp()))
                    .background(Color.White)

                    .defaultMinSize(minWidth = 920f.pxToDp(), minHeight = 450f.pxToDp())

                    .padding(
                        start = 40f.pxToDp(),
                        end = 43f.pxToDp(),
                        top = 105f.pxToDp(),
                        bottom = 41f.pxToDp()
                    ),
            ){
                Column(
                    modifier = Modifier.background(Color.White),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        style = Typography.titleMedium,
                        fontSize = 35f.pxToSp(),
                        color = Color1E1E1E
                    )

                    Spacer(modifier = Modifier.height(50f.pxToDp()))

                    Text(
                        text = desc,
                        textAlign = TextAlign.Start,
                        style = Typography.titleMedium,
                        fontSize = 16f.pxToSp(),
                        color = Color757575
                    )

                    Spacer(modifier = Modifier.height(60f.pxToDp()))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 86f.pxToDp())
                    ) {

                        TextButton(
                            modifier = Modifier
                                .weight(0.5f)
                                //.fillMaxWidth(0.5f)
                                .clip(shape = RoundedCornerShape(18f.pxToDp()))
                                .background(ColorECF0F8),
                            //modifier =Modifier.background(0) ,
                            onClick = {
                                onClickCancel()
                            }) {
                                Text("이전", style = Typography.bodySmall ,fontSize = 25f.pxToSp(), color = Color757575)
                            }
                        
                        Spacer(modifier = Modifier.width(20f.pxToDp()))
                        
                        TextButton(
                            modifier = Modifier
                                .weight(0.5f)
                                //.fillMaxWidth(0.5f)
                                .clip(shape = RoundedCornerShape(18f.pxToDp()))
                                .background(Color4C96FF),
                            onClick = {
                                onClickConfirm()
                            }) {
                            Text("완료",style = Typography.bodySmall, fontSize = 25f.pxToSp(), color = Color.White)
                        }
                    }
                }

            }

        }
    }


}
package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.ui.theme.PrimaryButtonColor
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun CustomButton(
    contentColor : Color,
    containerColor: Color,
    buttonWidth : Float = 553f,
    buttonHeight : Float = 86f,
    text : String,
    leftIcon : Painter? = null,
    rightIcon : Painter? = null,
    withBoard : Boolean = false,
    enabled : Boolean = true,
    btnClick : ()-> Unit? = {}
) {

    var boardColor : Color

        if(withBoard){
            boardColor =  contentColor
        } else{
            boardColor = containerColor
        }

    Button(
        enabled = enabled ,
        onClick = {
            btnClick()
        },
        modifier = Modifier
            .defaultMinSize(
                minWidth = buttonWidth.pxToDp(),
                minHeight = buttonHeight.pxToDp()
            )
            .border(
                width = 3f.pxToDp(),
                color = boardColor,
                shape = RoundedCornerShape(18f.pxToDp())
            ),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        leftIcon?.let {
            Image(
                modifier = Modifier.size(11f.pxToDp(), 22f.pxToDp()),
                painter = leftIcon,
                contentDescription = null
            )
        }

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = Typography.labelMedium,
            color = contentColor,
            //modifier = Modifier.weight(1f),
            fontSize = 18f.pxToSp()
        )
        rightIcon?.let {
            Image(
                modifier = Modifier.size(11f.pxToDp(), 22f.pxToDp()),
                painter = rightIcon,
                contentDescription = null
            )
        }
    }
}
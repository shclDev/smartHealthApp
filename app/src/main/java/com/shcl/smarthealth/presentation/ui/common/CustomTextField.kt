package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    focusedBoardColor : Color,
    unfocusedBoardColor : Color,
    placeHolder : String,
    valueChanged : (String)-> Unit
    ){

    var inputText by remember{ mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.onFocusChanged {
            if(!it.isFocused){
                focusManager.clearFocus()
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBoardColor,
            unfocusedBorderColor = unfocusedBoardColor
        ),
        textStyle = Typography.titleSmall,
        shape = RoundedCornerShape(18f.pxToDp()),
        value = inputText,
        onValueChange = { inputText = it
            valueChanged(inputText)
        },
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolder ,
                style = Typography.titleSmall,
                color = Color757575,
                fontSize = 20f.pxToSp()
            )
        })


}
package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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
    keyOption : KeyboardOptions? = KeyboardOptions(keyboardType = KeyboardType.Text),
    valueChanged : (String)-> Unit
    ){

    var inputText by remember{ mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val keyboardType = keyOption?.let {  keyOption } ?:run{
        KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    OutlinedTextField(
        modifier = modifier.addFocusCleaner(focusManager),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBoardColor,
            unfocusedBorderColor = unfocusedBoardColor
        ),
        keyboardOptions = keyboardType,
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

fun Modifier.addFocusCleaner(focusManager : FocusManager , doOnClear: ()-> Unit ={}) : Modifier{
    return this.pointerInput(Unit){
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        }) }
}

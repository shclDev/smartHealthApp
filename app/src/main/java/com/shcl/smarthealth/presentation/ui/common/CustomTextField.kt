package com.shcl.smarthealth.presentation.ui.common

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color143F91
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
    isHiddenText : Boolean = false,
    placeHolder : String,
    keyOption : KeyboardOptions? = KeyboardOptions(keyboardType = KeyboardType.Text),
    valueChanged : (String)-> Unit,
    withCertificationIcon : Boolean = false
    ){

    var inputText by remember{ mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val keyboardType = keyOption?.let {  keyOption } ?:run{
        KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    var isHidden by rememberSaveable { mutableStateOf(isHiddenText) }

    OutlinedTextField(
        modifier = modifier
            .addFocusCleaner(focusManager)
            .padding( 17f.pxToDp()),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBoardColor,
            unfocusedBorderColor = unfocusedBoardColor
        ),
        visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardType,
        textStyle = Typography.titleSmall,
        shape = RoundedCornerShape(18f.pxToDp()),
        value = inputText,
        onValueChange = { inputText = it
            valueChanged(inputText)
        },
        trailingIcon = {
            if(withCertificationIcon){
                OutlinedButton(
                    modifier = Modifier
                        .background(Color.White)
                        .border(
                            width = 2f.pxToDp(),
                            color = Color143F91,
                            shape = RoundedCornerShape(16f.pxToDp())
                        ) ,
                    onClick = {

                    }){
                    Text("인증" , fontSize = 15f.pxToSp() , color = Color143F91)
                }
            }
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

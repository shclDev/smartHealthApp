package com.shcl.smarthealth.presentation.view.register.component
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.CustomTextField
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.ColorD4D9E1


@Composable
fun UserPictureNickName() {
    var nickname by remember { mutableStateOf("") }

    Column {
        Image(
            modifier = Modifier
                .size(200.pxToDp(), 200.pxToDp())
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.reg_picture),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(25f.pxToDp()))

        CustomTextField(
            modifier = Modifier.defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 86f.pxToDp()),
            focusedBoardColor = Color143F91,
            unfocusedBoardColor = ColorD4D9E1,
            placeHolder = "별명(선택)",
            valueChanged = {
                nickname = it
                Log.d("register" , "nickname : ${nickname}")
            }
        )
    }

}
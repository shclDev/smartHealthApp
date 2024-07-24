package com.shcl.smarthealth.presentation.view.register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.ui.common.CustomTextField
import com.shcl.smarthealth.presentation.ui.common.LinearVerticalLine
import com.shcl.smarthealth.presentation.view.register.component.UserPictureNickName
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun RegisterScreen() {

    var phoneNumber by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("M") }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Row {
            LinearVerticalLine()
            Box ( modifier = Modifier.fillMaxWidth()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 204f.pxToDp(), horizontal = 80f.pxToDp())
                ) {
                    Text(
                        stringResource(id = R.string.join_member),
                        style = Typography.headlineMedium,
                        color = Color1E1E1E,
                        textAlign = TextAlign.Start,
                        fontSize = 30f.pxToSp()
                    )
                    Text(
                        stringResource(id = R.string.register_desc),
                        style = Typography.headlineMedium,
                        color = Color757575,
                        textAlign = TextAlign.Start,
                        fontSize = 15f.sp
                    )

                    Spacer(modifier = Modifier.height(80f.pxToDp()))

                    Row(horizontalArrangement = Arrangement.spacedBy(80f.pxToDp())){
                        UserPictureNickName()

                        Column(verticalArrangement = Arrangement.SpaceBetween){
                            Column {
                                Text(
                                    stringResource(id = R.string.register_phone_num),
                                    style = Typography.titleSmall,
                                    fontSize = 20f.pxToSp(),
                                    color = Color333333
                                )
                                Spacer(modifier = Modifier.height(25f.pxToDp()))
                                CustomTextField(
                                    modifier = Modifier.defaultMinSize(
                                        minWidth = 620f.pxToDp(),
                                        minHeight = 86f.pxToDp()
                                    ),
                                    focusedBoardColor = Color143F91,
                                    unfocusedBoardColor = ColorD4D9E1,
                                    placeHolder = "예 : 01012345678",
                                    valueChanged = {
                                        phoneNumber = it
                                        Log.d("register", "phoneNumber : ${phoneNumber}")
                                    })
                            }

                            Spacer(modifier = Modifier.height(80f.pxToDp()))

                            Column {
                                Text(stringResource(id = R.string.birthday) , style = Typography.titleSmall , fontSize = 20f.pxToSp() , color = Color333333)
                                Spacer(modifier = Modifier.height(25f.pxToDp()))
                                CustomTextField(
                                    modifier = Modifier.defaultMinSize(minWidth = 620f.pxToDp(), minHeight = 86f.pxToDp()),
                                    focusedBoardColor = Color143F91,
                                    unfocusedBoardColor = ColorD4D9E1,
                                    placeHolder = "주민번호 앞 여섯자리",
                                    valueChanged = {
                                        birthDay = it
                                        Log.d("register" , "birthDay : ${birthDay}")
                                    })
                            }
                        }

                        Column(verticalArrangement = Arrangement.SpaceBetween){
                            Column {
                                Text(
                                    stringResource(id = R.string.register_name),
                                    style = Typography.titleSmall,
                                    fontSize = 20f.pxToSp(),
                                    color = Color333333
                                )
                                Spacer(modifier = Modifier.height(25f.pxToDp()))
                                CustomTextField(
                                    modifier = Modifier.defaultMinSize(
                                        minWidth = 620f.pxToDp(),
                                        minHeight = 86f.pxToDp()
                                    ),
                                    focusedBoardColor = Color143F91,
                                    unfocusedBoardColor = ColorD4D9E1,
                                    placeHolder = "홍길동",
                                    valueChanged = {
                                        phoneNumber = it
                                        Log.d("register", "name : $name")
                                    })

                                Spacer(modifier = Modifier.height(80f.pxToDp()))



                            }


                        }



                    }
                }
            }
        }








    }

}
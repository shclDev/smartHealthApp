package com.shcl.smarthealth.presentation.view.register


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.CustomAlertDialog
import com.shcl.smarthealth.presentation.ui.common.CustomConfirmDialog
import com.shcl.smarthealth.presentation.ui.common.CustomGroupButtons
import com.shcl.smarthealth.presentation.ui.common.CustomTextField
import com.shcl.smarthealth.presentation.ui.common.LinearVerticalLine
import com.shcl.smarthealth.presentation.view.device.ConfirmDialog
import com.shcl.smarthealth.presentation.view.login.LoginStatus
import com.shcl.smarthealth.presentation.view.register.component.MyImageArea
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.PrimaryButtonColor
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun RegisterScreen(nav: NavHostController , viewModel: RegisterViewModel  = hiltViewModel()) {

    var mobile by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("M") }
    var nickName by remember { mutableStateOf("") }
    var uri by remember { mutableStateOf<Uri?>(null) }

    val genderGroup : HashMap<String , Any> = hashMapOf("남성" to "M" , "여성" to "F")

    var showDialogState by remember { mutableStateOf(false) }
    var failMessage by remember { mutableStateOf("") }


    val signUpStatus by viewModel.signUpState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        if (signUpStatus == SignUpStatus.SIGNUP_SUCCESS) {
            nav.navigate(route = OuterScreen.terms.route)
        } else if(signUpStatus == SignUpStatus.SIGNUP_FAILED) {
            Log.d("signUp" , "signUp failed" )
            showDialogState = true
            failMessage = "이미 등록된 사용자입니다.\n로그인 진행해주세요."
            viewModel.signUpStateChange(SignUpStatus.NONE)
        }

        when{
            showDialogState->{

                CustomConfirmDialog(
                    show = showDialogState,
                    title = "회원 등록에 실패 하였습니다." ,
                    desc = "$failMessage",
                    onDismiss = {showDialogState = false},
                    onConfirm = {
                        showDialogState = false
                    },
                )

            }

        }

        Row {
            LinearVerticalLine()
            Box ( modifier = Modifier.fillMaxWidth()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 80f.pxToDp(),
                            end = 80f.pxToDp(),
                            top = 204f.pxToDp(),
                            bottom = 40f.pxToDp(),
                        )
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

                    Spacer(modifier = Modifier.height(50f.pxToDp()))

                    Row(horizontalArrangement = Arrangement.spacedBy(80f.pxToDp())){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            MyImageArea(
                                onSetUri = {
                                    uri = it
                                    Log.d("register" , it.toString())
                            })
                            Spacer(modifier = Modifier.height(25f.pxToDp()))

                            CustomTextField(
                                modifier = Modifier.defaultMinSize(minWidth = 300f.pxToDp(), minHeight = 86f.pxToDp()),
                                focusedBoardColor = Color143F91,
                                unfocusedBoardColor = ColorD4D9E1,
                                placeHolder = "별명(선택)",
                                valueChanged = {
                                    nickName = it
                                    Log.d("register" , "nickname : ${nickName}")
                                }
                            )
                        }

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
                                    keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.defaultMinSize(
                                        minWidth = 620f.pxToDp(),
                                        minHeight = 86f.pxToDp()
                                    ),
                                    focusedBoardColor = Color143F91,
                                    unfocusedBoardColor = ColorD4D9E1,
                                    placeHolder = "예 : 01012345678",
                                    valueChanged = {
                                        mobile = it
                                        Log.d("register", "phoneNumber : ${mobile}")
                                    })
                            }

                            Spacer(modifier = Modifier.height(80f.pxToDp()))

                            Column {
                                Text(stringResource(id = R.string.birthday) , style = Typography.titleSmall , fontSize = 20f.pxToSp() , color = Color333333)
                                Spacer(modifier = Modifier.height(25f.pxToDp()))
                                CustomTextField(
                                    keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier.defaultMinSize(minWidth = 620f.pxToDp(), minHeight = 86f.pxToDp()),
                                    focusedBoardColor = Color143F91,
                                    unfocusedBoardColor = ColorD4D9E1,
                                    placeHolder = "주민번호 앞 여섯자리",
                                    valueChanged = {
                                        birthDate = it
                                        Log.d("register" , "birthDate : ${birthDate}")
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
                                        name = it
                                        Log.d("register", "name : $name")
                                    })

                                Spacer(modifier = Modifier.height(80f.pxToDp()))
                            }

                            Column {

                                Text(
                                    stringResource(id = R.string.register_gender),
                                    style = Typography.titleSmall,
                                    fontSize = 20f.pxToSp(),
                                    color = Color333333
                                )
                                Spacer(modifier = Modifier.height(25f.pxToDp()))

                                CustomGroupButtons(
                                    options = genderGroup ,
                                    unSelectedColor = ColorD4D9E1 ,
                                    selectedColor = Color143F91,
                                    selectionChanged = { it->
                                        gender = it
                                        Log.d("register" , it)
                                    }
                                )

                                Spacer(modifier = Modifier.height(80f.pxToDp()))
                                    Button(
                                        onClick = {

                                            uri?.let {
                                                val validation =  viewModel.validationUserInfo(
                                                    name = name,
                                                    nickName = nickName,
                                                    birthDate = birthDate,
                                                    gender = gender,
                                                    mobile = mobile,
                                                    picture = it)

                                                if(validation){
                                                    (uri?.let { uri } ?: run { null })?.let {
                                                        viewModel.signUpUser(
                                                            name = name,
                                                            nickName = nickName,
                                                            birthDate = birthDate,
                                                            gender = gender,
                                                            mobile = mobile,
                                                            picture = it
                                                        )
                                                    }
                                                }else{
                                                    failMessage = "입력하지 않은 정보가 있는지 다시 확인해 주세요"
                                                    showDialogState = true
                                                }
                                            }
                                        },
                                        shape = RoundedCornerShape(18.pxToDp()),
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = ButtonDefaults.buttonColors(
                                            contentColor = Color.White,
                                            containerColor = PrimaryButtonColor
                                        )
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.next),
                                            textAlign = TextAlign.Center,
                                            style = Typography.labelMedium,
                                            color = Color.White,
                                            modifier = Modifier.weight(1f),
                                            fontSize = 18f.pxToSp()
                                        )
                                        Image(
                                            modifier =
                                            Modifier
                                                .size(11f.pxToDp(), 22f.pxToDp()),
                                            painter = painterResource(id = R.drawable.arrow),
                                            contentDescription = null
                                        )
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}
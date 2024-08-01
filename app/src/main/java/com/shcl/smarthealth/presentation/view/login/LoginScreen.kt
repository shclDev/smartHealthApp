package com.shcl.smarthealth.presentation.view.login

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.min
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.utils.PreferencesManager
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.CustomTextField
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.PrimaryButtonColor
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun LoginScreen(nav: NavHostController , viewModel: LoginViewModel = hiltViewModel()) {

    //viewModel.loggedUserCheck()
    val loginStatus by viewModel.loginState.collectAsStateWithLifecycle()
    val loggedUser by viewModel.loggedUserState.collectAsStateWithLifecycle()

    var mobile by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }


    Box {
        if (loginStatus == LoginStatus.LOGIN_SUCCESS) {
            nav.navigate(route = OuterScreen.home.route)
        } else if (loginStatus == LoginStatus.LOGIN_FAILED) {
            Log.d("login", "login failed")
        }

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            leftSide()
            if(loggedUser.size >= 1){
                autoLoginSide(nav = nav, viewModel = viewModel , users = loggedUser)
            }else{
                loginSide(nav = nav, viewModel = viewModel)
            }
        }
    }


}

@Composable
fun leftSide(){


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .background(brush = Brush.verticalGradient(BackGroundColor)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        Image(
            modifier = Modifier.size(497.pxToDp() , 278.pxToDp()),
            painter = painterResource(id = R.drawable.logo_main),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginSide(nav: NavHostController , viewModel : LoginViewModel){

    var phoneNum by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 123.pxToDp(), top = 123.pxToDp(), end = 123.pxToDp()),
            contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ){

            Text(text = stringResource(id = R.string.login_welcome) , style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(30.pxToDp()))
            Text(text = stringResource(id = R.string.login_desc) , style = Typography.bodySmall )
            Spacer(modifier = Modifier.height(30.pxToDp()))
            Column {
                Text(text = stringResource(id = R.string.handphone) , style = Typography.labelMedium , color = Color333333)
                Spacer(modifier = Modifier.height(25f.pxToDp()))
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 86f.pxToDp()),
                    focusedBoardColor = Color333333,
                    unfocusedBoardColor = ColorD4D9E1,
                    placeHolder = stringResource(id = R.string.handphone_hint),
                    keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                    valueChanged = {
                        phoneNum = it
                        Log.d("register" , "phoneNum : ${phoneNum}")
                    }
                )

                /*
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(86f.pxToDp()),
                        //.padding(horizontal = 40.pxToDp() , vertical = 30.pxToDp()),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color333333,
                        unfocusedBorderColor = ColorD4D9E1),
                    value = phoneNum,
                    onValueChange = { phoneNum = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    placeholder = {
                        Text(text = stringResource(id = R.string.handphone_hint) ,
                                        style = Typography.titleMedium,
                                        fontSize = 15f.pxToSp(),
                                        color = Color757575
                    )
                } )
                 */
            }

            Spacer(modifier = Modifier.height(20.pxToDp()))
            Column {
                Text(text = stringResource(id = R.string.birthday) , style = Typography.labelMedium , color = Color333333)
                Spacer(modifier = Modifier.height(25f.pxToDp()))
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 86f.pxToDp()),
                    focusedBoardColor = Color333333,
                    unfocusedBoardColor = ColorD4D9E1,
                    placeHolder = stringResource(id = R.string.birthday_hint),
                    keyOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                    valueChanged = {
                        birthDay = it
                        Log.d("register" , "birthDay : ${phoneNum}")
                    }
                )
                /*
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(86f.pxToDp()),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color333333,
                        unfocusedBorderColor = ColorD4D9E1),
                    value = birthDay,
                    onValueChange = { birthDay = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.birthday_hint) ,
                            style = Typography.titleMedium,
                            fontSize = 15f.pxToSp(),
                            color = Color757575)
                    })*/
            }
            Spacer(modifier = Modifier.height(80.pxToDp()))

            Button(
                onClick = {
                    //login check
                    viewModel.signCheck()
                    //viewModel.signIn(mobile =  phoneNum , birthDate = birthDay)
                    //nav.navigate(route = OuterScreen.home.route)
                    //nav.navigate(route = OuterScreen.deviceScan.route)
                },
                shape = RoundedCornerShape(18.pxToDp()),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = PrimaryButtonColor)
            ) {
              Text(text= stringResource(id = R.string.join) ,
                  style = Typography.labelMedium , color = Color.White)
            }

            Spacer(modifier = Modifier.height(40.pxToDp()))

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    nav.navigate(route = OuterScreen.registser.route)
            }){
                Text(
                    text= stringResource(id = R.string.join_member) ,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    style = Typography.labelMedium , color = Color333333)
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun autoLoginSide(nav: NavHostController , viewModel : LoginViewModel , users : List<UserRoom>){

    var phoneNum by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 123.pxToDp(), top = 123.pxToDp(), end = 123.pxToDp()),
            contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ){

            Text(text = stringResource(id = R.string.login_welcome) , style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(30.pxToDp()))
            Text(text = stringResource(id = R.string.autologin_desc) , style = Typography.bodySmall )
            Spacer(modifier = Modifier.height(30.pxToDp()))
            Box(modifier = Modifier.fillMaxSize()
                .border(
                shape = RoundedCornerShape(10f.pxToDp()),
                width = 1f.pxToDp(),
                color = Color757575
            )) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 131f.pxToDp(), vertical = 52f.pxToDp())
                        ,
                    columns = GridCells.Fixed(2),
                    state = rememberLazyGridState()
                ) {

                    items(users.size + 1, span = { index ->
                        GridItemSpan(1)
                    }) { index ->
                        if (index == users.size) {

                            RegisterCard(onClick = {
                                nav.navigate(route = OuterScreen.registser.route)
                            })

                        } else {
                            val user = users[index]
                            LoginUserCard(
                                user = user,
                                onClick = {
                                    PreferencesManager.saveData("accessToken", user.token)
                                    nav.navigate(route = OuterScreen.home.route)
                                    viewModel.lastedUserLoginUpdate(user)
                                })
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.pxToDp()))
        }
    }
}


@Composable
private fun RegisterCard(
    onClick: ()->Unit
){

    Box(
        modifier = Modifier.clickable { onClick() }
    ){
        Column (modifier = Modifier.align(Alignment.Center)){

            AsyncImage(
                model = R.drawable.add_user,
                //painter = painterResource(id = R.drawable.top_profile_img),
                error = painterResource(id = R.drawable.dummy_profile),
                contentScale = ContentScale.Crop,
                contentDescription = "User",
                modifier = Modifier
                    .size(120.pxToDp())
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(25f.pxToDp()))

            Text("계정 추가" , textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(25f.pxToDp()))

        }
    }
}


@Composable
private fun LoginUserCard(
    user : UserRoom,
    onClick: ()->Unit
){

    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 245f.pxToDp(), minHeight = 266f.pxToDp())
            .clickable { onClick() }
    ){
       Column (modifier = Modifier.align(Alignment.Center)){

           AsyncImage(
               model = ImageRequest.Builder(LocalContext.current).data(Uri.parse(user.profileUri)).crossfade(true).build(),
               //painter = painterResource(id = R.drawable.top_profile_img),
               error = painterResource(id = R.drawable.dummy_profile),
               contentScale = ContentScale.Crop,
               contentDescription = "User",
               modifier = Modifier
                   .size(120.pxToDp())
                   .clip(CircleShape)
                   .align(Alignment.CenterHorizontally)
           )

           Spacer(modifier = Modifier.height(25f.pxToDp()))

           Text(text = "${user.name}" , textAlign = TextAlign.Center , fontSize = 24f.pxToSp() , style = Typography.titleSmall )

           Spacer(modifier = Modifier.height(25f.pxToDp()))

       }
    }
}
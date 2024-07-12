package com.shcl.smarthealth.presentation.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.PrimaryButtonColor
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun LoginScreen(nav: NavHostController) {

    Row (
        modifier = Modifier.fillMaxSize()
    ){
        leftSide()
        rightSide(nav)
    }
    
    
}

@Composable
fun leftSide(){

    val logo = painterResource(id = R.drawable.logo_main)

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
            painter = logo,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rightSide(nav : NavHostController){

    var phoneNum by remember { mutableStateOf(TextFieldValue("")) }
    var birthDay by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 123.dp, top = 123.dp, end = 123.dp),
            contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
        ){

            Text(text = stringResource(id = R.string.login_welcome) , style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(id = R.string.login_desc) , style = Typography.bodySmall )
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Text(text = stringResource(id = R.string.handphone) , style = Typography.labelMedium , color = Color333333)
                //Spacer(modifier = Modifier.height(25.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 86f.dp),
                    //modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp,30.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color333333,
                        unfocusedBorderColor = ColorD4D9E1),
                    value = phoneNum,
                    onValueChange = { phoneNum = it } ,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.handphone_hint) ,
                                        style = Typography.titleMedium,
                                        color = Color757575
                    )
                } )

            }

            Spacer(modifier = Modifier.height(20.pxToDp()))
            Column {
                Text(text = stringResource(id = R.string.birthday) , style = Typography.labelMedium , color = Color333333)
                //Spacer(modifier = Modifier.height(25.pxToDp()))
                OutlinedTextField(
                    //modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp,30.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color333333,
                        unfocusedBorderColor = ColorD4D9E1),
                    value = birthDay,
                    onValueChange = { birthDay = it },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.birthday_hint) ,
                            style = Typography.titleMedium,
                            color = Color757575)
                    })
            }
            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    nav.navigate(route = OuterScreen.home.route)
                    //nav.navigate(route = OuterScreen.deviceScan.route)
                },
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = PrimaryButtonColor)
            ) {
              Text(text= stringResource(id = R.string.join) ,
                  style = Typography.labelMedium , color = Color.White)
            }

            Spacer(modifier = Modifier.height(40.dp))

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                nav.navigate(route = OuterScreen.home.route)
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
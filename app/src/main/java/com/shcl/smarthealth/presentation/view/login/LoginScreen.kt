package com.shcl.smarthealth.presentation.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun rightSide(nav : NavHostController){

    var handPhoneNum by remember { mutableStateOf(TextFieldValue("")) }
    var birthDay by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 123.pxToDp(), top = 123.pxToDp() , end = 123.pxToDp()),
            contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)

        ){

            Text(text = stringResource(id = R.string.login_welcome) , style = TextStyle(fontFamily = FontFamily.Default , fontSize = 50f.pxToSp() , fontWeight = FontWeight.W700) )
            Spacer(modifier = Modifier.height(30f.pxToDp()))
            Text(text = stringResource(id = R.string.login_desc) , style = TextStyle(fontFamily = FontFamily.Default , fontSize = 20f.sp , fontWeight = FontWeight.W500) )
            Spacer(modifier = Modifier.height(30f.pxToDp()))
            Column {
                Text(text = stringResource(id = R.string.handphone) , style = TextStyle(fontFamily = FontFamily.Default , fontWeight = FontWeight.W500 , fontSize = 26f.pxToSp()))
                Spacer(modifier = Modifier.height(25f.pxToDp()))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = handPhoneNum,
                    onValueChange = { handPhoneNum = it } ,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.handphone_hint) ,
                                        style = TextStyle(
                                            fontFamily = FontFamily.SansSerif ,
                                            fontWeight = FontWeight.W500 ,
                                            fontSize = 20f.sp ,
                                            color =  Color757575 ) )

                } )

            }

            Spacer(modifier = Modifier.height(20.pxToDp()))
            Column {
                Text(text = stringResource(id = R.string.birthday) , style = TextStyle(fontFamily = FontFamily.SansSerif , fontWeight = FontWeight.W500 , fontSize = 26.sp))
                Spacer(modifier = Modifier.height(25.pxToDp()))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = birthDay,
                    onValueChange = { birthDay = it },
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.birthday_hint) ,
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif ,
                            fontWeight = FontWeight.W500 ,
                            fontSize = 20f.pxToSp() ,
                            color =  Color757575 ) )

                    } )

            }
            Spacer(modifier = Modifier.height(125.pxToDp()))

            Button(
                onClick = {
                    nav.navigate(route = OuterScreen.home.route)
                    //nav.navigate(route = OuterScreen.deviceScan.route)
                },
                shape = RoundedCornerShape(18.pxToDp()),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = PrimaryButtonColor)
            ) {
              Text(text= stringResource(id = R.string.join) ,
                  style = TextStyle(
                      fontFamily = FontFamily.SansSerif ,
                      fontWeight = FontWeight.W700 ,
                      fontSize = 32f.pxToSp() ,
                      color = Color.White))
            }

            Spacer(modifier = Modifier.height(40.pxToDp()))

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                nav.navigate(route = OuterScreen.home.route)
            }){
                Text(
                    text= stringResource(id = R.string.join_member) ,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif ,
                        fontWeight = FontWeight.W700 ,
                        fontSize = 32f.pxToSp() ,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline))
            }
        }

    }




}
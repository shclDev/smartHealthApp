package com.shcl.smarthealth.presentation.view.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.CustomButton
import com.shcl.smarthealth.presentation.ui.common.CustomCheckBox
import com.shcl.smarthealth.presentation.ui.common.LinearVerticalLine
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color193889
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF6F6F6
import com.shcl.smarthealth.ui.theme.Typography


@Composable
fun TermsOfUseScreen(nav: NavHostController) {

    val termsCheckGroup : HashMap<String , Any> = hashMapOf(stringResource(id = R.string.terms_agree_chk_yes) to "Y" , stringResource(id = R.string.terms_agree_chk_no) to "N")
    var termsAgree : Boolean by remember { mutableStateOf(false) }


    Box(

    ){
        Row(){
            LinearVerticalLine()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 80f.pxToDp(),
                        top = 70f.pxToDp(),
                        bottom = 40f.pxToDp(),
                        end = 80f.pxToDp()
                    )){
                Text(text = stringResource(id = R.string.terms_title) , style = Typography.headlineMedium , fontSize = 25f.pxToSp() , color = Color1E1E1E )
                Text(text = stringResource(id = R.string.terms_desc) , style = Typography.headlineMedium , fontSize = 25f.pxToSp() , color = Color757575)
                Spacer(modifier = Modifier.height(40f.pxToDp()))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(527f.pxToDp())
                        .border(
                            width = 2f.pxToDp(),
                            color = ColorD4D9E1,
                            shape = RoundedCornerShape(
                                topStart = 18f.pxToDp(),
                                topEnd = 18f.pxToDp()
                            )
                        )
                        .padding(40f.pxToDp())
                        .verticalScroll(rememberScrollState()),
                    text = stringResource(id = R.string.terms_body) , style = Typography.bodyMedium , fontSize = 20f.pxToSp() , color = Color333333)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80f.pxToDp())

                        //.padding(vertical = 30f.pxToDp(), horizontal = 40f.pxToDp())
                        .background(color = ColorF6F6F6)
                        .border(
                            width = 2f.pxToDp(),
                            color = ColorD4D9E1,
                            shape = RoundedCornerShape(
                                bottomStart = 18f.pxToDp(),
                                bottomEnd = 18f.pxToDp()
                            )
                        )
                        .padding(start = 40f.pxToDp())

                ){
                    Text(text = stringResource(id = R.string.terms_agree) , style = Typography.bodyLarge , fontSize = 25f.pxToSp() , color = Color333333)
                    Spacer(modifier = Modifier.width(230f.pxToDp()))
                    CustomCheckBox(
                        initSelect = stringResource(id = R.string.terms_agree_chk_no),
                        options = termsCheckGroup , unSelectedColor = ColorD4D9E1  , selectedColor = Color193889 , checkboxSize = 37f , selectionChanged = {
                            if(it.compareTo("Y") == 0){
                                termsAgree = true
                            }else{
                                termsAgree = false
                            }
                        })
                }

                Spacer(modifier = Modifier.height(80f.pxToDp()))

                  Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100f.pxToDp())
                ){
                      CustomButton(
                          buttonWidth = 553f,
                          btnClick = {
                              nav.navigate(route = OuterScreen.registser.route)
                          },
                          contentColor = Color143F91, containerColor = Color.White , withBoard = true, text = "이전" , leftIcon = painterResource(id = R.drawable.left_arrow))
                      CustomButton(
                          enabled = termsAgree,
                          buttonWidth = 553f,
                          contentColor = Color.White, containerColor = Color143F91 , text = "확인" , rightIcon = painterResource(id = R.drawable.arrow), btnClick = {
                              nav.navigate(route = OuterScreen.registerComplete.route)
                          })

                  }

            }

            }
    }





}
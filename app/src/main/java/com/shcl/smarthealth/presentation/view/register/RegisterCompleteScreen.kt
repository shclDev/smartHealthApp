package com.shcl.smarthealth.presentation.view.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.presentation.navigation.OuterScreen
import com.shcl.smarthealth.presentation.ui.common.CustomButton
import com.shcl.smarthealth.presentation.ui.common.LinearVerticalLine
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun RegisterCompleteScreen(nav: NavHostController) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearVerticalLine()
            Column(
                modifier = Modifier.padding(start = 650f.pxToDp() , end = 650f.pxToDp() ),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(id = R.string.register_complete_title) , style = Typography.headlineLarge , fontSize = 30f.pxToSp() , color = Color1E1E1E)
                Spacer(modifier = Modifier.height(250f.pxToDp()))
                Text(stringResource(id = R.string.register_complete_desc) , style = Typography.headlineLarge , fontSize = 15f.pxToSp() , color = Color757575, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(161f.pxToDp()))
                CustomButton(buttonWidth = 720f, contentColor = Color.White, containerColor = Color143F91, text ="등록완료", rightIcon = painterResource(id = R.drawable.arrow) , btnClick = {
                    nav.navigate(route = OuterScreen.home.route)
                } )
            }
        }

}
package com.shcl.smarthealth.presentation.view.dashboard.component


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.ExoPlayerView
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun NutritionComponent(){

    var playerShow by remember { mutableStateOf(false) }

    Box {

        when{
            playerShow->{
                ExoPlayerView(
                    onClickCancel = {
                        Log.d("player" , "onClickCancel")
                        playerShow = false},
                    onShowDialog = playerShow,
                    title = "영양 권고",
                    uri = "https://cdn.pixabay.com/video/2017/12/20/13497-248644899_large.mp4"  )
            }
        }

        Column {
            Row {
                Text("영양 권고", style = Typography.headlineLarge, fontSize = 25f.sp)
                Spacer(modifier = Modifier.width(19f.pxToDp()))
                Image(
                    modifier = Modifier
                        .size(33.pxToDp(), 33.pxToDp())
                        .align(alignment = Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.icon_spoon),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(15f.pxToDp()))
            Text("식이 섬유가 많은 통곡물,콩류 섭취를 매 끼니 합니다.", style = Typography.labelSmall, fontSize = 15f.sp)
            Spacer(modifier = Modifier.height(20f.pxToDp()))
            Image(
                modifier = Modifier
                    .size(680f.pxToDp(), 214f.pxToDp())
                    .clickable {
                        if(!playerShow) playerShow = true },
                painter = painterResource(id = R.drawable.dashboard_nutrion),
                contentDescription = null
            )
        }
    }

}
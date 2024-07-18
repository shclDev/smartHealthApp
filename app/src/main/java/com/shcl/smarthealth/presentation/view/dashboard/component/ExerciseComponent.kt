package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun ExerciseComponent(){

    Box {
        Column {
            Row {
                Text("운동 권고", style = Typography.headlineLarge , fontSize = 25f.sp)
                Spacer(modifier = Modifier.width(19f.pxToDp()))
                Image(
                    modifier = Modifier.size(33.pxToDp(), 33.pxToDp()).align(alignment = Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.icon_dumbbell),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(15f.pxToDp()))
            Text("아령,건강밴드 등을 이용한 상하체 근력 운동을 주 2회 시행합니다.", style = Typography.labelSmall , fontSize = 15f.sp)
            Spacer(modifier = Modifier.height(25f.pxToDp()))
            Image(
                modifier = Modifier.size(680f.pxToDp(), 214f.pxToDp()),
                painter = painterResource(id = R.drawable.dashboard_execrise),
                contentDescription = null
            )
        }
    }

}
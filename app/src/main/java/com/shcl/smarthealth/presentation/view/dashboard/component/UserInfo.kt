package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shcl.smarthealth.R
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardViewModel
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD49E1
import com.shcl.smarthealth.ui.theme.ColorF3F4F6
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun UserInfo(viewModel: DashBoardViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier.size(700.dp , 180.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                contentDescription = "User",
                modifier = Modifier.size(120.dp).clip(CircleShape).border(2.dp , Color.LightGray)
            )
            Spacer(modifier = Modifier.width(40.dp))
            Column {
                Row{
                    Text("혜정님, 안녕하세요" , style = Typography.headlineLarge , color = Color1E1E1E)
                    Image(
                        modifier = Modifier.size(63.dp , 63.dp),
                        painter = painterResource(id = R.drawable.ani_hand),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                        .padding(horizontal = 40.dp , vertical = 28.dp)
                        .border(width = 1.dp , color = ColorD49E1 , shape = RoundedCornerShape(18)
                        )){
                    Text("성별 " , style = Typography.bodySmall)
                    Text("여 " , style = Typography.bodySmall)
                    Text("나이 " , style = Typography.bodySmall)
                    Text("65세 " , style = Typography.bodySmall)
                }
            }

        }

    }


}
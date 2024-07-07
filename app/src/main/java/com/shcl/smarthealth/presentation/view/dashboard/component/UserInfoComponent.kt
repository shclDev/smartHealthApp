package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.hilt.navigation.compose.hiltViewModel
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardViewModel
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.ColorD49E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun UserInfo(viewModel: DashBoardViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 700f.pxToDp(), minHeight = 180f.pxToDp())
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                contentDescription = "User",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
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
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 530f.pxToDp(), minHeight = 80f.pxToDp())
                        .align(Alignment.Start)
                        .border(
                            width = 1.dp, color = ColorD49E1, shape = RoundedCornerShape(18)
                        ).padding(horizontal = 40f.pxToDp(), vertical = 28f.pxToDp())){
                    Text("성별 " , style = Typography.bodySmall)
                    Text("여 " , style = Typography.bodySmall)
                    Text("나이 " , style = Typography.bodySmall)
                    Text("65세 " , style = Typography.bodySmall)
                }
            }

        }

    }


}
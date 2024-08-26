package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color333333
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorF1F4F9
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun WatchComponent(data : OverallResponse?){

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(18f.pxToDp()))
            .background(color = ColorF1F4F9)
            .size(width = 1020f.pxToDp(), height = 220f.pxToDp())
            .padding(20.dp)
    ) {
        Row {
            data?.stepsInfo?.let {
                Column(modifier = Modifier
                    .weight(0.5f)
                    .verticalScroll(rememberScrollState())) {
                    Row(horizontalArrangement = Arrangement.spacedBy(20f.pxToDp())) {
                        Text(
                            "${it.measureTypeName}",
                            style = Typography.bodyLarge,
                            fontSize = 20f.sp,
                            color = Color1E1E1E
                        )

                        Text(
                            "${it.currentValue ?: 0}",
                            style = Typography.bodyLarge,
                            fontSize = 25f.sp,
                            color = Color1E1E1E,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            "${it.dataUnitName ?: "보"}",
                            style = Typography.bodyLarge,
                            fontSize = 20f.sp,
                            color = Color757575,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        "어제보다 110보 적게 걸었습니다.",
                        style = Typography.bodyLarge,
                        fontSize = 16f.sp,
                        color = Color333333,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = stringResource(id = R.string.dummy_info),
                        style = Typography.bodyLarge,
                        fontSize = 14f.sp,
                        maxLines = 3,
                        color = Color333333,
                        textAlign = TextAlign.Start
                    )
                    //Spacer(modifier = Modifier.height(43f.pxToDp()))
                }
            }?: run{
                Text(modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically) ,text = "데이터를 불러오지 못했습니다.", fontSize = 14f.pxToSp())
            }
           VerticalDivider()
            data?.heartRateVariabilityInfo?.let {
                Column(modifier = Modifier.weight(0.5f).verticalScroll(rememberScrollState())) {
                    Row(horizontalArrangement = Arrangement.spacedBy(20f.pxToDp())) {
                        Text(
                            "${it.measureTypeName}",
                            style = Typography.bodyLarge,
                            fontSize = 20f.sp,
                            color = Color1E1E1E
                        )

                        Text(
                            "${it.currentValue ?: 0}",
                            style = Typography.bodyLarge,
                            fontSize = 25f.sp,
                            color = Color1E1E1E,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            "${it.dataUnitName ?: "ms"}",
                            style = Typography.bodyLarge,
                            fontSize = 20f.sp,
                            color = Color757575,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        "측정된 심박변이도는 높은 편이에요. 이는 우리 몸이 안정\n" +
                                " 상태에 있다고 볼 수 있어요. 하지만 이는 ~~~~",
                        style = Typography.bodyLarge,
                        fontSize = 14f.sp,
                        maxLines = 3,
                        color = Color333333,
                        textAlign = TextAlign.Center
                    )
                    //Spacer(modifier = Modifier.height(43f.pxToDp()))
                }
            } ?: run{
                Text(
                    modifier = Modifier
                        .weight(0.5f)
                        .align(Alignment.CenterVertically) , text = "데이터를 불러오지 못했습니다." , fontSize = 14f.pxToSp())
            }
        }


    }


}
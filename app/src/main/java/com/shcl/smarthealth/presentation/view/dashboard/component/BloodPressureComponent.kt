package com.shcl.smarthealth.presentation.view.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardViewModel
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD49E1
import com.shcl.smarthealth.ui.theme.ColorF1F4F9
import com.shcl.smarthealth.ui.theme.Typography
import kotlin.math.roundToInt


@Composable
fun BloodPressureComponent(bloodPressure : BloodPressureRoom?){

    //viewModel.getLastedBloodPressure()
    //val bloodPressure by viewModel.bloodPressure.collectAsState()

    Box(
        modifier = Modifier
            .background(color = ColorF1F4F9)
            .border(width = 1.dp, color = ColorD49E1, shape = RoundedCornerShape(18.dp))
            .padding(30.dp)
            .defaultMinSize(minWidth = 496f.pxToDp(), minHeight = 360f.pxToDp())
    ){
        Column() {
            Row (){
                Image(
                    modifier = Modifier.size(70f.pxToDp(), 70f.pxToDp()).align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.systolic_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(30f.pxToDp()))

                bloodPressure?.systolic?.let {
                    Column() {
                        Text("수축기 혈압" , style = Typography.headlineMedium )

                        Row {
                            Text("${bloodPressure?.systolic?.roundToInt()}" , style = Typography.labelSmall)
                            Text("${bloodPressure?.systolicUnit}", style = Typography.labelSmall , color = Color757575)
                        }

                        Text("그래프 영역")
                    }
                } ?: Column() {
                    Text("수축기 혈압",style = Typography.headlineMedium)
                    Text("- mmHg" , style = Typography.bodyMedium, color = Color757575)
                    Text("그래프 영역")
                }

            }

            Spacer(modifier = Modifier.height(40f.pxToDp()))

            Row () {
                Image(
                    modifier = Modifier.size(70f.pxToDp(), 70f.pxToDp()).align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.diastolic_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(30f.pxToDp()))

                bloodPressure?.diastolic?.let{
                    Column() {
                        Text("이완기 혈압" , style = Typography.headlineMedium )
                        Row {
                            Text("${bloodPressure?.diastolic?.roundToInt()}" ,  style = Typography.labelSmall)
                            Text("${bloodPressure?.diastolicUnit}", style = Typography.labelSmall , color = Color757575)
                        }
                        Text("그래프 영역")
                    }
                }?: Column() {
                    Text("이완기 혈압",style = Typography.headlineMedium)
                    Text("- mmHg" , style = Typography.bodyMedium, color = Color757575)
                    Text("그래프 영역")
                }
            }
        }
    }

}
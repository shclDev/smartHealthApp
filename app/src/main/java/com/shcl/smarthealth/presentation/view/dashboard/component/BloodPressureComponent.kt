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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import com.shcl.smarthealth.ui.theme.Color1E1E1E
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD4D9E1
import com.shcl.smarthealth.ui.theme.ColorF1F4F9
import com.shcl.smarthealth.ui.theme.Typography
import kotlin.math.roundToInt


@Composable
fun BloodPressureComponent(bloodPressure : BloodPressureRoom?){

    //viewModel.getLastedBloodPressure()
    //val bloodPressure by viewModel.bloodPressure.collectAsState()

    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 496f.pxToDp(), minHeight = 360f.pxToDp())
            .border(width = 1.dp, color = ColorD4D9E1, shape = RoundedCornerShape(18.dp))
            .background(color = ColorF1F4F9)
            .padding(30.dp)
    ){
        Column() {
            Row (){
                Image(
                    modifier = Modifier
                        .size(70f.pxToDp(), 70f.pxToDp())
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.systolic_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(30f.pxToDp()))

                bloodPressure?.systolic?.let {
                    Column() {
                        Text("수축기 혈압" , style = Typography.bodyLarge , fontSize = 20f.sp , color = Color1E1E1E )

                        Row {
                            Text("${bloodPressure.systolic.roundToInt()}" , style = Typography.bodyLarge , color = Color1E1E1E , fontSize = 20f.sp)
                            Text("${bloodPressure.systolicUnit}", style = Typography.bodyLarge , color = Color1E1E1E , fontSize = 15f.sp)
                        }
                        Spacer(modifier = Modifier.height(16f.pxToDp()))
                        Text("그래프 영역" , style = Typography.bodyLarge , fontSize = 12f.sp)
                    }
                } ?: Column() {
                    Text("수축기 혈압",style = Typography.bodyLarge , color = Color1E1E1E , fontSize = 20f.sp)
                    Text("- mmHg" , style = Typography.bodyLarge, color = Color1E1E1E , fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(16f.pxToDp()))
                    Text("그래프 영역" , style = Typography.bodyLarge , fontSize = 12f.sp)
                }

            }

            Spacer(modifier = Modifier.height(40f.pxToDp()))

            Row () {
                Image(
                    modifier = Modifier
                        .size(70f.pxToDp(), 70f.pxToDp())
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.diastolic_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(30f.pxToDp()))

                bloodPressure?.diastolic?.let{
                    Column() {
                        Text("이완기 혈압" , style = Typography.bodyLarge , fontSize = 20.sp)
                        Row {
                            Text("${bloodPressure?.diastolic?.roundToInt()}" ,  style = Typography.bodyLarge , fontSize = 20.sp)
                            Text("${bloodPressure?.diastolicUnit}", style = Typography.bodyLarge , color = Color1E1E1E , fontSize = 15.sp)
                        }
                        Spacer(modifier = Modifier.height(16f.pxToDp()))
                        Text("그래프 영역" , fontSize = 12f.sp)
                    }
                }?: Column() {
                    Text("이완기 혈압",style = Typography.bodyLarge , fontSize = 20.sp)
                    Text("- mmHg" , style = Typography.bodyLarge, color = Color1E1E1E , fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(16f.pxToDp()))
                    Text("그래프 영역", style = Typography.bodyLarge, fontSize = 12f.sp)

                }
            }
        }
    }

}
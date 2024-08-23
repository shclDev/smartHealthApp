package com.shcl.smarthealth.presentation.view.dashboard

import android.content.res.loader.ResourcesProvider
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shcl.smarthealth.R
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.ui.common.CustomAlertDialog
import com.shcl.smarthealth.presentation.view.dashboard.component.BloodPressureComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.ExerciseComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.GlucoseComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.NutritionComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.PursePressureComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.UserInfo
import com.shcl.smarthealth.presentation.view.dashboard.component.WatchComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.WeatherComponent
import com.shcl.smarthealth.presentation.view.dashboard.component.WeightComponent
import com.shcl.smarthealth.presentation.view.device.DeviceViewModel
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import kotlinx.coroutines.flow.collect


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun DashBoardScreen(nav : NavHostController, viewModel: DashBoardViewModel = hiltViewModel() , deviceViewModel: DeviceViewModel = hiltViewModel()){

    /*
    var timer1Progress by remember{ mutableStateOf(false) }
    var timer2Progress by remember{ mutableStateOf(false) }
    var timer3Progress by remember { mutableStateOf(false) }

    var openAlertDialog by remember{ mutableStateOf(false) }

    val timer1 = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timer1Progress = true
        }
        override fun onFinish() {
            timer1Progress = false
            Log.d("timer" , "timer1 finish")
            viewModel.getLastedBloodPressure()
        }
    }

    val timer2 = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timer2Progress = true
        }
        override fun onFinish() {
            timer2Progress = false
            Log.d("timer" , "timer2 finish")
            viewModel.getLastedWeight()
        }
    }

    val timer3 = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timer3Progress = true

            if(millisUntilFinished == 6000L){
                deviceViewModel.isensAllRecords()
            }
        }
        override fun onFinish() {
            timer3Progress = false
            Log.d("timer" , "timer3 finish")
            viewModel.getLastedBloodPressure()
        }
    }*/


    //viewModel.getNutrionAdvice()
    val nutritionAdvice by viewModel.nutritionAdvice.collectAsStateWithLifecycle()

    //viewModel.getLastedBloodPressure()
    val bloodPressure by viewModel.bloodPressure.collectAsStateWithLifecycle()
    
    //viewModel.getLastedWeight()
    val weight by viewModel.bodyComposition.collectAsStateWithLifecycle()

    //viewModel.getLastedGlucose()
    val glucose by viewModel.glucose.collectAsStateWithLifecycle()

    //viewModel.getCurrentWeather()
    val weather by viewModel.weatherResponse.collectAsStateWithLifecycle()


    //viewModel.getUserInfo()
    val userInfoDB by viewModel.userInfo.collectAsStateWithLifecycle()

    //viewModel.getUserInfoServer()
    val userInfoServer by viewModel.userInfoServer.collectAsStateWithLifecycle()

    //viewModel.getUserPicture()
    val userPicture by viewModel.userInfoPicture.collectAsStateWithLifecycle()

    val dashboardData by viewModel.dashboardData.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 63f.pxToDp(), vertical = 63f.pxToDp())
            .background(Color.White)

    ) {

        Column{

            Row(
                horizontalArrangement = Arrangement.Start) {
                UserInfo(userInfoServer , userPicture )
                Spacer(modifier = Modifier.width(283f.pxToDp()))
                /*
                WeatherComponent(
                    weatherResponse = weather,
                    refreshClick = {
                        Log.d("weather" , "call weather")
                        viewModel.getCurrentWeather() })*/
            }

                /*
                Button(
                    onClick = {
                        deviceViewModel.getOmronMeasurementRecord(
                            type = RequestType.DataTransfer,
                            device = DiscoveredDevice(
                                deviceCategory = OHQDeviceCategory.BloodPressureMonitor,
                                address = "28:FF:B2:68:72:EC",
                                localName = "BLESmart_0000056428FFB26872EC",
                                rssi = -47
                            )
                        )
                        timer1.start()

                    }) {
                    if (timer1Progress) {
                        Text("데이터 가져오는 중..", fontSize = 12f.sp)
                    } else {
                        Text("혈압 데이터", fontSize = 12f.sp)
                    }

                }
                Button(
                    onClick = {
                        deviceViewModel.getOmronMeasurementRecord(
                            type = RequestType.DataTransfer,
                            device = DiscoveredDevice(
                                deviceCategory = OHQDeviceCategory.BodyCompositionMonitor,
                                address = "00:5F:BF:04:A4:09",
                                localName = "BLESmart_00010408005FBF04A409",
                                rssi = -44
                            )
                        )
                        timer2.start()
                    }) {
                    if (timer2Progress) {
                        Text("데이터 가져오는 중..", fontSize = 12f.sp)
                    } else {
                        Text("체중 데이터", fontSize = 12f.sp)
                    }
                }

                Button(
                    onClick = {
                        openAlertDialog = true
                        when{
                            openAlertDialog->{
                                /*
                                CustomAlertDialog(
                                    title = "건강정보 입력을 마치시겠습니까?",
                                    desc = "아직 수정할 내용이 이썩나, 답하지 못한 질문은 없는지 확인해주세요.",
                                    onClickConfirm = {},
                                    onClickCancel = { openAlertDialog = false},
                                    icon =  R.drawable.icon_spoon
                            )*/
                        }
                    }


                        //deviceViewModel.isensConnect("74:46:B3:4E:30:F7")
                        //timer3.start()
                    }) {
                    if (timer3Progress) {
                        Text("데이터 가져오는 중..", fontSize = 12f.sp)
                    } else {
                        Text("혈당 데이터", fontSize = 12f.sp)
                    }
                }

            }*/
            Spacer(modifier = Modifier.height(23f.pxToDp()))
            Row(
                horizontalArrangement = Arrangement.spacedBy(40f.pxToDp())
            ) {
                NutritionComponent()
                ExerciseComponent()
            }
            Spacer(modifier = Modifier.height(40f.pxToDp()))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20f.pxToDp())
            ) {
                BloodPressureComponent(dashboardData)
                Column(verticalArrangement = Arrangement.spacedBy(20f.pxToDp())) {
                    Row(horizontalArrangement = Arrangement.spacedBy(20f.pxToDp())) {
                        GlucoseComponent(dashboardData)
                        PursePressureComponent(dashboardData)
                        WeightComponent(dashboardData)
                    }
                    WatchComponent(data = dashboardData)
                }

            }

            /*
            nutritionAdvice?.let{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    text = it
                )
            }*/
        }
    }

    }




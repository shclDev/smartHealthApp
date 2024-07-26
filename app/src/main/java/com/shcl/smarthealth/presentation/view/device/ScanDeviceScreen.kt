 package com.shcl.smarthealth.presentation.view.device

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp
import jp.co.ohq.ble.enumerate.OHQDeviceCategory

 @Composable
fun ScanDeviceScreen(
    nav : NavHostController,
    viewModel : DeviceViewModel = hiltViewModel(),
){
    val omronDevicesStatFlow by viewModel.omronDeviceState.collectAsStateWithLifecycle()
    val omronMeasurementStatus by viewModel.omronMeasurementState.collectAsStateWithLifecycle()
    val isensDeviceStateFlow by viewModel.isensDeviceState.collectAsStateWithLifecycle()
    val isensMeausurementStatus by viewModel.isensMeasurementState.collectAsStateWithLifecycle()

    var sessionStatus = ""

    //val deviceTestStateFlow by viewModel.deviceState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()){
        Row {
            Box{
                Row(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Column {
                        Text("혈압/체중계 디바이스")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            modifier = Modifier.width(200f.pxToDp()),
                            onClick = {
                            viewModel.omronScanDevice()
                        }) {
                            Text("디바이스 검색",fontSize = 12f.sp , textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            modifier = Modifier.width(200f.pxToDp()),
                            onClick = { viewModel.omronStopScan() }) {
                            Text("멈춤",fontSize = 12f.sp, textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.height(10f.pxToDp()))
                        LazyColumn() {
                            omronDevicesStatFlow.scannedDevices.let{devices->
                                items(devices) { device->

                                    device?.let{
                                        deviceItem(viewModel , it)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10f.pxToDp()))
                        Text(text= omronMeasurementStatus.toString() , color = Color.Black , fontSize = 15f.pxToSp())
                    }

                }
            }

            Spacer(modifier = Modifier.width(50.dp))

            Box{
                Row(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Column {
                        Text("혈당 디바이스")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            modifier = Modifier.width(200f.pxToDp()),
                            onClick = {
                            viewModel.isensScanDevice()
                        }) {
                            Text("디바이스 검색", fontSize = 12f.sp, textAlign = TextAlign.Center)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            modifier = Modifier.width(200f.pxToDp()),
                            onClick = { viewModel.isensStopScan() }) {
                            Text("멈춤" , fontSize = 12f.sp, textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.height(10f.pxToDp()))
                        LazyColumn() {
                            isensDeviceStateFlow.scannedDevices.let{devices->
                                items(devices) { device->
                                    device?.let{
                                        iSensDeviceItem(viewModel ,it)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10f.pxToDp()))
                        Text(text= isensMeausurementStatus.toString() , color = Color.Black , fontSize = 15f.pxToSp())
                    }

                }
            }

        }
    }
}

@Composable
fun iSensDeviceItem(viewModel:DeviceViewModel, device : BluetoothDevice){

    var showDialogState by remember { mutableStateOf(false) }
    var showRegisterDialogState by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .defaultMinSize(minWidth = 400f.pxToDp())
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(18.dp))
            .padding(12.dp)
            .clickable {
                if (!showDialogState) {
                    showDialogState = true
                }
            }
    ){
        Column(){
            Text(text = "${device.address}" , fontSize = 15f.sp )

            Spacer(modifier = Modifier.height(10.dp))

            /*
            device.name?.let{
                Text(text = "${device.localName}")
            }

            device.modelName?.let{
                Text(text="${device.modelName}")
            }*/
        }

        Button(
            onClick = {

                if (!showRegisterDialogState) {
                    showRegisterDialogState = true
                }

            }) {
            Text("등록" , fontSize = 12f.sp)
        }
    }

    when{
        showDialogState ->{
            ConfirmDialog(
                show = showDialogState,
                title = "디바이스 데이터 받기" ,
                desc = "데이터를 받겠습니까?",
                onDismiss = {showDialogState = false},
                onConfirm = {
                    Log.d("isens","transfer data request!!")
                    viewModel.isensAllRecords()
                    //viewModel.getOmronMeasurementRecord(device , RequestType.DataTransfer)
                    showDialogState = false
                },
            )
        }
    }

    when{
        showRegisterDialogState->{
            ConfirmDialog(
                show = showRegisterDialogState,
                title = "디바이스 등록" ,
                desc = "해당 디바이스를 등록 하시겠습니까?",
                onDismiss = {showRegisterDialogState = false},
                onConfirm = {
                    Log.d("isens","register device")
                    viewModel.isensConnect(device.address)
                    if (ActivityCompat.checkSelfPermission(
                            GlobalVariables.context,
                            Manifest.permission.BLUETOOTH_CONNECT
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }
                    viewModel._registerToDBDevice(DiscoveredDevice(address = device.address , localName = device.name , deviceCategory = OHQDeviceCategory.Glucose))
                    //viewModel.getOmronMeasurementRecord(device , RequestType.Paring)
                    showRegisterDialogState = false
                },
            )

        }

    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun deviceItem(viewModel:DeviceViewModel, device : DiscoveredDevice){

    var showDialogState by remember { mutableStateOf(false) }
    var showRegisterDialogState by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .defaultMinSize(minWidth = 400f.pxToDp())
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(18.dp))
            .padding(12.dp)
            .clickable {
                if (!showDialogState) {
                    showDialogState = true
                }
            }
    ){
        Column(){
            Text(text = "${device.address}", fontSize = 15.sp )

            Spacer(modifier = Modifier.height(10.dp))

            device.localName?.let{
                Text(text = "${device.localName}" , fontSize = 10f.sp)
            }

            device.modelName?.let{
                Text(text="${device.modelName}",fontSize = 10f.sp)
            }
        }

        Button(
            onClick = {

                if (!showRegisterDialogState) {
                    showRegisterDialogState = true
                }

            }) {
            Text("등록" , fontSize = 12f.sp)
        }
    }

    when{
        showDialogState ->{
            ConfirmDialog(
                show = showDialogState,
                title = "디바이스 데이터 받기" ,
                desc = "데이터를 받겠습니까?",
                onDismiss = {showDialogState = false},
                onConfirm = {
                    Log.d("omron","transfer data request!!")
                    viewModel.getOmronMeasurementRecord(device , RequestType.DataTransfer)
                    showDialogState = false
                },
            )
        }
    }

    when{
        showRegisterDialogState->{
            ConfirmDialog(
                show = showRegisterDialogState,
                title = "디바이스 등록" ,
                desc = "해당 디바이스를 등록 하시겠습니까?",
                onDismiss = {showRegisterDialogState = false},
                onConfirm = {
                    Log.d("omron","register device")
                    viewModel.getOmronMeasurementRecord(device , RequestType.Paring)
                    showRegisterDialogState = false
                },
            )

        }

    }


}


@Composable
fun ConfirmDialog(
    show : Boolean,
    title : String,
    desc : String,
    onDismiss : ()-> Unit,
    onConfirm : ()-> Unit

){

    if(show){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton ={
                TextButton(onClick = onConfirm) {
                    Text("Confiram")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            },
            title = { Text(text = title) },
            text = { Text(text = desc )}

        )
    }

}
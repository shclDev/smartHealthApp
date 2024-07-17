package com.shcl.smarthealth.presentation.view.device

import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.utils.pxToSp

@Composable
fun ScanDeviceScreen(
    nav : NavHostController,
    viewModel : DeviceViewModel = hiltViewModel(),
){
    val omronDevicesStatFlow by viewModel.omronDeviceState.collectAsState()
    val omronMeasurementStatus by viewModel.omronMeasurementState.collectAsState()
    val isensDeviceStateFlow by viewModel.isensDeviceState.collectAsState()
    val isensMeausurementStatus by viewModel.isensMeasurementState.collectAsState()

    var sessionStatus = ""

    //val deviceTestStateFlow by viewModel.deviceState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()){

        Row {

            Box{
                Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Column {
                        Text("혈압/체중계 디바이스")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            viewModel.omronScanDevice()
                        }) {
                            Text("scan device")
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { viewModel.omronStopScan() }) {
                            Text("stop device")
                        }

                        LazyColumn() {
                            omronDevicesStatFlow.scannedDevices.let{devices->
                                items(devices) { device->

                                    device?.let{
                                        deviceItem(viewModel , it)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text= omronMeasurementStatus.toString() , color = Color.Black , fontSize = 15f.pxToSp())
                }
            }

            Spacer(modifier = Modifier.width(50.dp))

            Box{
                Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Column {
                        Text("혈당 디바이스")
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            viewModel.isensScanDevice()
                        }) {
                            Text("scan device")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = { viewModel.isensStopScan() }) {
                            Text("stop device")
                        }

                        LazyColumn() {
                            isensDeviceStateFlow.scannedDevices.let{devices->
                                items(devices) { device->
                                    device?.let{
                                        iSensDeviceItem(viewModel ,it)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(100.dp))

                    Text(text= isensMeausurementStatus.toString() , color = Color.Black , fontSize = 20f.pxToSp())
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
            .fillMaxWidth(0.7f)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(18.dp))
            .padding(12.dp)
            .clickable {
                if (!showDialogState) {
                    showDialogState = true
                }
            }
    ){
        Column(){
            Text(text = "${device.address}" , fontSize = 10f.sp )

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
            Text("register" , fontSize = 9f.sp)
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
            .fillMaxWidth(0.7f)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(18.dp))
            .padding(12.dp)
            .clickable {
                if (!showDialogState) {
                    showDialogState = true
                }
            }
    ){
        Column(){
            Text(text = "${device.address}" )

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
            Text("register" , fontSize = 9f.sp)
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
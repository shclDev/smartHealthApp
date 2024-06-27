package com.shcl.smarthealth.presentation.view.device

import android.Manifest
import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ScanDeviceScreen(
    nav : NavHostController,
    viewModel : OmronDeviceViewModel = hiltViewModel()
){
    val devicesStatFlow by viewModel.state.collectAsState()
    val testFlow by viewModel.testState.collectAsState()
    val measurementStatus by viewModel.measurementState.collectAsState()


    //val deviceTestStateFlow by viewModel.deviceState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){

        Column {

            Button(onClick = {
                viewModel.scanDevice() }) {
                Text("scan device")
            }

            Button(onClick = { viewModel.stopScan() }){
                Text("stop device")
            }

            Text(text = "${testFlow}", color = Color.White)

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                /*
                items(state.scannedDevices){ device->


                }*/

                devicesStatFlow.scannedDevices.let{devices->
                    items(devices) {
                        device->

                        device?.let{
                            deviceItem(viewModel , it)
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun deviceItem(viewModel:OmronDeviceViewModel,device : DiscoveredDevice){

    var showDialogState by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(18.dp))
            .padding(12.dp)
            .clickable {
                if (!showDialogState) {
                    showDialogState = true
                }
            }
    ){
        Column(){
            Text(text = "${device.address}" , color = Color.White)

            Spacer(modifier = Modifier.height(10.dp))

            device.localName?.let{
                Text(text = "${device.localName}" , color = Color.White)
            }
        }

        Button(
            onClick = {


            }) {
            Text("register" , color = Color.White)
        }
    }

    when{
        showDialogState ->{
            transferDataConfirmDialog(
                show = showDialogState,
                title = "디바이스 데이터 받기" ,
                desc = "데이터를 받겠습니까?",
                onDismiss = {showDialogState = false},
                onConfirm = { viewModel.getMeasurementRecord(device)  }
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun transferDataConfirmDialog(
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
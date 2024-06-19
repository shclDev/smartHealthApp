package com.shcl.smarthealth.presentation.view.device

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice

@Composable
fun ScanDeviceScreen(
    nav : NavHostController,
    viewModel : OmronDeviceViewModel = hiltViewModel()
){
    val devices by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){

        Button(onClick = { viewModel.scanDevice() }) {
            Text("scan device")
        }

        /*
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            /*
            items(state.scannedDevices){ device->


            }*/

            items(devices.scannedDevices) {device->
                deviceItem(device)
            }
        }*/


        /*
        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.isScanning){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }*/

    }


}

@Composable
fun deviceItem(device : DiscoveredDevice){

    Card(

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            device.address?.let{
                Text(text = "${device.address}")

            }

            Spacer(modifier = Modifier.height(10.dp))

            device.modelName?.let{
                Text(text = "${device.modelName}")
                }
            }
        }

}
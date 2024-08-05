package com.shcl.smarthealth.presentation.ui.common

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun featureThatRequiresPermission(
    onPermissionStatus : @Composable (Boolean) ->Unit
) : Boolean{

    val permissions = listOf(
        android.Manifest.permission.BLUETOOTH_SCAN,
        android.Manifest.permission.BLUETOOTH_ADVERTISE,
        android.Manifest.permission.BLUETOOTH_CONNECT,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.READ_MEDIA_IMAGES
    )

    var openAlertDialog by remember{ mutableStateOf(false) }

    // 요청한 권한 상태
    val permissionStates = rememberMultiplePermissionsState(permissions)

    if(permissionStates.allPermissionsGranted){
        onPermissionStatus(true)
        return true
    }else{
        permissionStates.permissions.forEach{
            if(it.status.isGranted){
                Log.d("permission" ,"${it.permission} is granted")
            }else{
                openAlertDialog = true;
                when{
                    openAlertDialog->{
                        AlertDialog(
                            modifier = Modifier
                                .size(450.dp, 300.dp)
                                .padding(10.dp)
                                .shadow(elevation = 20.dp),
                            shape = RoundedCornerShape(24.dp),
                            containerColor = Color.White,
                            title = { Text(text = "권한 설정") },
                            text = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "앱 사용을 위해 해당 권한(${it.permission}이 필요합니다.")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { openAlertDialog = false }) {
                                    Text("Cancel" , style = TextStyle(color = Color.Black))
                                }
                            },
                            onDismissRequest = { openAlertDialog = false },
                            confirmButton = {
                                TextButton(onClick = {
                                    it.launchPermissionRequest()
                                    //openAlertDialog = false
                                }) {
                                    Text("OK" , style = TextStyle(color = Color.Black))
                                }
                            },
                        )

                    }
                }

            }
        }

        if(permissionStates.allPermissionsGranted){
            onPermissionStatus(true)
            return true
        }else{
            return false
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermission() {
    val blePermissionState = rememberPermissionState  (
        android.Manifest.permission.BLUETOOTH_CONNECT
    )

    if (blePermissionState.status.isGranted) {
        Text("블루투스 권한 획득")
    } else {
        Column {
            val textToShow = if (blePermissionState.status.shouldShowRationale) {
                "블루투스 권한은 필수 입니다. 블루투스 권한을 획득해 주세요"
            } else {
                "블루투스 권한이 필요 합니다."
            }
            Text(textToShow)
            Button(onClick = { blePermissionState.launchPermissionRequest() }) {
                Text("권한 요청")
            }
        }
    }
}
package com.shcl.smarthealth.presentation.view.device

data class ScanDeviceState(
    val isScanning : Boolean = false,
    val scannedDevices : List<String> = listOf(),
    val error : String = ""
)

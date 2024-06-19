package com.shcl.smarthealth.presentation.view.device

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice

data class ScanDeviceState(
    val scannedDevices : List<DiscoveredDevice> = listOf(),
    val error : String = ""
)

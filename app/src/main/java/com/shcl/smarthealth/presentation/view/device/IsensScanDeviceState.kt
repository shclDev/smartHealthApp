package com.shcl.smarthealth.presentation.view.device

import android.bluetooth.BluetoothDevice
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice

data class IsensScanDeviceState (
    val scannedDevices : MutableList<BluetoothDevice?> = mutableListOf(),
    val error : String = ""
)
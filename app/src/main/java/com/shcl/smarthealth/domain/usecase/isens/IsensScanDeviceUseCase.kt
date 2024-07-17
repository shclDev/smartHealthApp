package com.shcl.smarthealth.domain.usecase.isens

import android.bluetooth.BluetoothDevice
import com.shcl.smarthealth.domain.repository.IsensRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class IsensScanDeviceUseCase @Inject constructor(
    private val repository: IsensRepository
){
    fun onScan() : Flow<MutableList<BluetoothDevice?>> = repository.onScan()
    fun stopScan() = repository.stopScan()
    fun startScan() = repository.startScan()
    fun connect(address : String) = repository.connect(address)
}
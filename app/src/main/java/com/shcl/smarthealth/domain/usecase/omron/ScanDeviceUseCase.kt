package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanDeviceUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    fun onScan() : Flow<List<DiscoveredDevice?>> = repository.onScan()

    fun testStateFlow(scope : CoroutineScope) : Flow<Int> = repository.testStateFlow()

    fun searchDevices(){
        repository.searchDevice()
    }

    fun stopDevice(){
        repository.stopScan()
    }
}
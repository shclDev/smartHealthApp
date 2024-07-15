package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    suspend fun registerDeviceToDB(discoveredDevice: DiscoveredDevice) = repository.registerDeviceDataToDB(discoveredDevice)

    //fun paringDevice(discoveredDevice: DiscoveredDevice) = repository.pairing(discoveredDevice)

}
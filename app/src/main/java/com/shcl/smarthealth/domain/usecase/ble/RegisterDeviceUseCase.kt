package com.shcl.smarthealth.domain.usecase.ble

import android.util.Log
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.utils.Utils
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    suspend fun registerDeviceToDB(discoveredDevice: DiscoveredDevice) = repository.registerDeviceDataToDB(discoveredDevice)

    //fun paringDevice(discoveredDevice: DiscoveredDevice) = repository.pairing(discoveredDevice)

}
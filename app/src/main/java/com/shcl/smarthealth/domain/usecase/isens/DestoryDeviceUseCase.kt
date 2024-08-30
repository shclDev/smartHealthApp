package com.shcl.smarthealth.domain.usecase.isens

import com.shcl.smarthealth.domain.repository.IsensRepository
import javax.inject.Inject

class DestoryDeviceUseCase @Inject constructor(
    private val repository: IsensRepository
){
    fun disconnect() = repository.disconnectDevice()
    fun destory() = repository.destorySDK()
}
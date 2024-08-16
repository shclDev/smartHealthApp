package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class GetDeviceUseCase @Inject constructor(private val repository: OmronRepository){

    suspend fun getDevice(category : String) = repository.getDeviceByCategory(category)
}
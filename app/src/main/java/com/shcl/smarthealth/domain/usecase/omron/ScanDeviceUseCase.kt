package com.shcl.smarthealth.domain.usecase.omron

import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanDeviceUseCase @Inject constructor(
    private val repository: OmronRepository
) {

    /*
    suspend operator fun invoke() : Flow<Resource<Flow<List<DiscoveredDevice>>>> = flow{

        try{
            emit(Resource.Loading<List<DiscoveredDevice>>())
            val devices = repository.searchDevice()
            emit(Resource.Success<List<DiscoveredDevice>>(devices))
        }catch(e : HttpException){
            emit(Resource.Error<List<DiscoveredDevice>>(e.localizedMessage ?: "An Unexpected error occured"))
        }catch(e : IOException){
            emit(Resource.Error<List<DiscoveredDevice>>(e.localizedMessage?:"IOException"))
        }

    }*/

    fun onScan() : Flow<List<DiscoveredDevice?>> = repository.onScan()


    fun testStateFlow(scope : CoroutineScope) : Flow<Int> = repository.testStateFlow()

    fun searchDevices(){
        repository.searchDevice()
    }

    fun stopDevice(){
        repository.stopScan()
    }
}
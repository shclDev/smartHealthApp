package com.shcl.smarthealth.domain.usecase.ble

import android.os.Handler
import com.shcl.smarthealth.common.Resource
import com.shcl.smarthealth.domain.model.omron.DeviceInfo
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import jp.co.ohq.ble.OHQDeviceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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

    operator suspend fun invoke() = repository.searchDevice()

}
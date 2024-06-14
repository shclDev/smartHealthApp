package com.shcl.smarthealth.domain.usecase.ble

import android.os.Handler
import com.shcl.smarthealth.common.Resource
import com.shcl.smarthealth.data.ble.DeviceInfo
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

    val BATCHED_SCAN_INTERVAL = 10000;

    private lateinit var ohqDeviceManager : OHQDeviceManager
    private lateinit var handler : Handler

    init {

        handler = Handler()
        ohqDeviceManager = OHQDeviceManager.sharedInstance();

    }

    operator fun invoke() : Flow<Resource<DeviceInfo>> = flow{

        try{
            emit(Resource.Loading<DeviceInfo>())
            val device = repository.searchDevice();
            //emit(Resource.Success<DeviceInfo>(device))
        }catch(e : HttpException){
            emit(Resource.Error<DeviceInfo>(e.localizedMessage ?: "An Unexpected error occured"))
        }catch(e : IOException){
            emit(Resource.Error<DeviceInfo>(e.localizedMessage?:"IOException"))
        }

    }

}
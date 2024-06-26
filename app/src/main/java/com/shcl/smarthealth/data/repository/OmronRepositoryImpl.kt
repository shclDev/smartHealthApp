package com.shcl.smarthealth.data.repository

import android.util.Log
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import jp.co.ohq.ble.OHQDeviceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class OmronRepositoryImpl (
    private val omronDeviceDataSource : OmronDeviceDataSource
) : OmronRepository {


    override suspend fun connect() {

    }

    override fun searchDevice() {
        omronDeviceDataSource.startScan()
    }

    override suspend fun getBloodPressureData() {
        TODO("Not yet implemented")
    }

    override suspend fun getBodyData() {
        TODO("Not yet implemented")
    }

    override suspend fun pairing() {
        TODO("Not yet implemented")
    }

    override fun stopScan() {
        omronDeviceDataSource.stopScan()
    }

    override fun onScan(): Flow<List<DiscoveredDevice?>> {
        return omronDeviceDataSource.onScaned(mutableListOf())


        /*
        return flow {
            omronDeviceDataSource.onScaned(null).collect{
                emit(it)
            }
        }*/


        /*
        return omronDeviceDataSource.onScaned(null).map {
            it.map{
                discoveredDevice ->

                DiscoveredDevice(
                    address = discoveredDevice.address,
                    advertisementData = discoveredDevice.advertisementData,
                    localName = discoveredDevice.localName,
                    modelName = discoveredDevice.modelName
                )

            }

        }*/
    }

    override fun testStateFlow(): Flow<Int> {
        return omronDeviceDataSource.testStateFlow()
    }
    //return omronDeviceDataSource.onScaned(null)


}
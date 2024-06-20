package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import jp.co.ohq.ble.OHQDeviceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OmronRepositoryImpl (
    private val omronDeviceDataSource : OmronDeviceDataSource
) : OmronRepository{



    override suspend fun connect() {

    }

    override suspend fun searchDevice(): Flow<List<DiscoveredDevice>> {
       return flow{
           emit(omronDeviceDataSource.startScan())
       }
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


}
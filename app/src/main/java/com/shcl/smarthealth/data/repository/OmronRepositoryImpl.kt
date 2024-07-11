package com.shcl.smarthealth.data.repository

import android.util.Log
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.OmronDeviceDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.omron.BloodPressure
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.repository.OmronRepository
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import jp.co.ohq.ble.OHQDeviceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class OmronRepositoryImpl (
    private val omronDeviceDataSource : OmronDeviceDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource
) : OmronRepository {


    override suspend fun connect() {

    }

    override fun searchDevice() {
        omronDeviceDataSource.startScan()
    }

    override fun getBloodPressureData(discoveredDevice: DiscoveredDevice?) : Flow<MeasurementRecordState> {
        return omronDeviceDataSource.getBloodPressureData(discoveredDevice)
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
    }

    override fun testStateFlow(): Flow<Int> {
        return omronDeviceDataSource.testStateFlow()
    }

    override suspend fun updateBloodPressureDataToDB(bloodPressureRoom: BloodPressureRoom) {
        measureRecordDataSource.updateBloodPressureToDB(bloodPressureRoom = bloodPressureRoom)
    }

    override fun getBloodPressureDataFromDB(userID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun registerDeviceDataToDB(discoveredDevice: DiscoveredDevice) {
        try{
            measureRecordDataSource.registerDeviceToDB(FoundDeviceRoom(
                userId = 1,
                address = discoveredDevice.address,
                deviceCategory = discoveredDevice.deviceCategory.toString(),
                rssi = discoveredDevice.rssi,
                modelName = discoveredDevice.modelName,
                localName = discoveredDevice.localName,
                timeStamp = Utils.getTimeStamp()
            ))
        }catch(e : Exception){
            Log.e("error" , "${e.message}")
        }
    }

    override fun getRegisterAllDevice(): Flow<List<FoundDeviceRoom>> {
        TODO("Not yet implemented")
    }


}
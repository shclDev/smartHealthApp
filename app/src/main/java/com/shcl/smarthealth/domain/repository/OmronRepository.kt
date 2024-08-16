package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow

interface OmronRepository {

    suspend fun connect()

    fun searchDevice()

    //get from device
    fun getDataTransfer(discoveredDevice: DiscoveredDevice? , type : RequestType) : Flow<MeasurementRecordState>

    suspend fun getBodyData()

    //fun pairing(discoveredDevice: DiscoveredDevice) : Flow<MeasurementRecordState>

    fun stopScan()

     fun onScan() : Flow<List<DiscoveredDevice?>>

     fun testStateFlow() : Flow<Int>

     //update to db
     suspend fun updateBloodPressureDataToDB(measureRecordRoom: BloodPressureRoom)
     //get from db
     fun getDataTransferFromDB(userID : Int)

    //update to db
    suspend fun updateBodyCompositionDataToDB(bodyCompositionRoom: BodyCompositionRoom)

    //get from db
    fun getBodyCompositionDataFromDB(userID : Int)

     //update to db
    suspend fun registerDeviceDataToDB(discoveredDevice: DiscoveredDevice)

    //get from db
    fun getRegisterAllDevice() : Flow<List<FoundDeviceRoom>>

    fun getDeviceByCategory(category : String) : Flow<FoundDeviceRoom>

}
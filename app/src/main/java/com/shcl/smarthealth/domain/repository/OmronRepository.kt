package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow

interface OmronRepository {

    suspend fun connect()

    fun searchDevice()

    //get from device
    fun getBloodPressureData(discoveredDevice: DiscoveredDevice?) : Flow<MeasurementRecordState>

    suspend fun getBodyData()

    suspend fun pairing()

    fun stopScan()

     fun onScan() : Flow<List<DiscoveredDevice?>>

     fun testStateFlow() : Flow<Int>

     //update to db
     suspend fun updateBloodPressureDataToDB(measureRecordRoom: BloodPressureRoom)
     //get from db
     fun getBloodPressureDataFromDB(userID : Int)

}
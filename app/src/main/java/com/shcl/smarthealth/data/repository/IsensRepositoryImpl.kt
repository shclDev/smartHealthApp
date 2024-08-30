package com.shcl.smarthealth.data.repository

import android.bluetooth.BluetoothDevice
import android.util.SparseArray
import com.isens.standard.ble.IBLE_GlucoseRecord
import com.shcl.smarthealth.data.repository.dataSource.IsensDeviceDataSource
import com.shcl.smarthealth.data.repository.dataSource.LocalDBDataSource
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import kotlinx.coroutines.flow.Flow

class IsensRepositoryImpl(
    private val isensDeviceDataSource: IsensDeviceDataSource,
    private val localDBDataSource: LocalDBDataSource
) : IsensRepository {

    override fun requestTimeSync() {
        TODO("Not yet implemented")
    }

    override fun requestAllRecords(){
        return isensDeviceDataSource.requestAllRecord()
    }

    override fun requestRecordAfterSequence(sequence: Int) {
        TODO("Not yet implemented")
    }

    override fun connect(address: String) {
        isensDeviceDataSource.connectDevice(address)
    }

    override fun disconnectDevice() {
        isensDeviceDataSource.disconnected()
    }

    override fun unPairDevice(address: String) {
        TODO("Not yet implemented")
    }

    override fun destorySDK() {
        isensDeviceDataSource.destoryDevice()
    }

    override fun onScan(): Flow<MutableList<BluetoothDevice?>> {
        return isensDeviceDataSource.onScaned(null)
    }

    override fun onDataTransfer(records: SparseArray<IBLE_GlucoseRecord>?): Flow<IsensGlucoseRecordState> {
        return isensDeviceDataSource.onDataTransfer(records)
    }

    override fun stopScan() {
       isensDeviceDataSource.stopScan()
    }

    override fun startScan() {
        isensDeviceDataSource.startScan()
    }

    override suspend fun updateGlucoseRecordToDB(glucoseRecordRoom: GlucoseRecordRoom) {
        localDBDataSource.updateGlucoseToDB(glucoseRecordRoom)
    }

    override fun getGlucoseRecordFromDB(userID: Int) {
        localDBDataSource.getGlucoseFromDB(userID)
    }

    override fun getDeviceByCategory(category: String): Flow<FoundDeviceRoom> {
        return localDBDataSource.getDeviceByCategory(category)
    }

}
package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.db.MeasurementRecordDao
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureListRoom
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import kotlinx.coroutines.flow.Flow

class MeasureRecordDataSourceImpl(private val measurementRecordDao: MeasurementRecordDao) : MeasureRecordDataSource{

    override fun getBloodPressureFromDB(userId: Int): Flow<BloodPressureRoom> = measurementRecordDao.getBloodPressureByUserID(userId)
    override suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom) = measurementRecordDao.addBloodPressure(bloodPressureRoom)
    override suspend fun registerDeviceToDB(foundDeviceRoom: FoundDeviceRoom) = measurementRecordDao.updateDevice(foundDeviceRoom)
    override fun getAllRegisterDevices(): Flow<List<FoundDeviceRoom>> = measurementRecordDao.getAllDevice()
    override suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom) = measurementRecordDao.addBodyComposition(bodyCompositionRoom)
    override fun getBodyCompositionFromDB(userId: Int): Flow<BodyCompositionRoom> = measurementRecordDao.getBodyCompositionByUserID(userId)

}
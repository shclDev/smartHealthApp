package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.db.MeasurementRecordDao
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import kotlinx.coroutines.flow.Flow

class MeasureRecordDataSourceImpl(private val measurementRecordDao: MeasurementRecordDao) : MeasureRecordDataSource{

    override fun getBloodPressureFromDB(userId: Int): Flow<BloodPressureRoom> = measurementRecordDao.getBloodPressureByUserID(userId)
    override suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom) = measurementRecordDao.addBloodPressure(bloodPressureRoom)
    override suspend fun registerDeviceToDB(foundDeviceRoom: FoundDeviceRoom) = measurementRecordDao.updateDevice(foundDeviceRoom)
    override fun getAllRegisterDevices(): Flow<List<FoundDeviceRoom>> = measurementRecordDao.getAllDevice()
    override suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom) = measurementRecordDao.addBodyComposition(bodyCompositionRoom)
    override fun getBodyCompositionFromDB(userId: Int): Flow<BodyCompositionRoom> = measurementRecordDao.getBodyCompositionByUserID(userId)
    override suspend fun updateGlucoseToDB(glucoseRecordRoom: GlucoseRecordRoom) = measurementRecordDao.addGlucoseRecord(glucoseRecordRoom)
    override fun getGlucoseFromDB(userId: Int) : Flow<GlucoseRecordRoom> = measurementRecordDao.getGlucoseRecordByUserID(userId)
    override suspend fun updateUser(userRoom: UserRoom) = measurementRecordDao.addUser(userRoom)
    override fun getUserFromDB(userId: Int) = measurementRecordDao.getUserByUserID(userId)
    override suspend fun updateLastedLoginUser(lastedLoginUserRoom: LastedLoginUserRoom) = measurementRecordDao.addLastedLoginUser(lastedLoginUserRoom)
    override fun getLastedLoginUser(): Flow<LastedLoginUserRoom>  = measurementRecordDao.getLastedLoginUserByUserID()
    override fun getAllUser(): Flow<MutableList<UserRoom>?> = measurementRecordDao.getAllUser()
}
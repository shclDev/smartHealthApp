package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.db.LocalDBDao
import com.shcl.smarthealth.data.repository.dataSource.LocalDBDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.FoundDeviceRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.TutorialRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import kotlinx.coroutines.flow.Flow

class LocalDBDataSourceImpl(private val localDBDao: LocalDBDao) :
    LocalDBDataSource {

    override fun getBloodPressureFromDB(userId: Int): Flow<BloodPressureRoom> = localDBDao.getBloodPressureByUserID(userId)
    override suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom) = localDBDao.addBloodPressure(bloodPressureRoom)
    override suspend fun registerDeviceToDB(foundDeviceRoom: FoundDeviceRoom) = localDBDao.updateDevice(foundDeviceRoom)
    override fun getAllRegisterDevices(): Flow<List<FoundDeviceRoom>> = localDBDao.getAllDevice()
    override suspend fun updateBodyCompositionToDB(bodyCompositionRoom: BodyCompositionRoom) = localDBDao.addBodyComposition(bodyCompositionRoom)
    override fun getBodyCompositionFromDB(userId: Int): Flow<BodyCompositionRoom> = localDBDao.getBodyCompositionByUserID(userId)
    override suspend fun updateGlucoseToDB(glucoseRecordRoom: GlucoseRecordRoom) = localDBDao.addGlucoseRecord(glucoseRecordRoom)
    override fun getGlucoseFromDB(userId: Int) : Flow<GlucoseRecordRoom> = localDBDao.getGlucoseRecordByUserID(userId)
    override suspend fun updateUser(userRoom: UserRoom) = localDBDao.addUser(userRoom)
    override fun getUserFromDB(userId: Int) = localDBDao.getUserByUserID(userId)
    override suspend fun updateLastedLoginUser(lastedLoginUserRoom: LastedLoginUserRoom) = localDBDao.addLastedLoginUser(lastedLoginUserRoom)
    override fun getLastedLoginUser(): Flow<LastedLoginUserRoom>  = localDBDao.getLastedLoginUserByUserID()
    override fun getAllUser(): Flow<List<UserRoom>> = localDBDao.getAllUser()
    override fun getDeviceByCategory(category: String): Flow<FoundDeviceRoom> = localDBDao.getDeviceByCategory(category)
    override suspend fun addTutorial(tutorialRoom: TutorialRoom) = localDBDao.addTutorial(tutorialRoom)
    override fun getTutorialByUserId(userId: Int): Flow<TutorialRoom?> = localDBDao.getTutorialByUserID(userId)
}
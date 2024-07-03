package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.db.MeasurementRecordDao
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureListRoom
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import kotlinx.coroutines.flow.Flow

class MeasureRecordDataSourceImpl(private val measurementRecordDao: MeasurementRecordDao) : MeasureRecordDataSource{

    override fun getBloodPressureFromDB(userId: Int): Flow<BloodPressureRoom> = measurementRecordDao.getBloodPressureByUserID(userId)
    override suspend fun updateBloodPressureToDB(bloodPressureRoom: BloodPressureRoom) = measurementRecordDao.addBloodPressure(bloodPressureRoom)
}
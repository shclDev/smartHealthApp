package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import kotlinx.coroutines.flow.Flow

class DashBoardRepositoryImpl(
    private val dashBoardRemoteDataSource: DashBoardRemoteDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource
)  : DashBoardRepository{

    override fun getNutritionAdvice(): Flow<String> {
        return dashBoardRemoteDataSource.getNutrtionAdvice()
    }

    override fun getLastedBloodPressure(): Flow<BloodPressureRoom> {
        return measureRecordDataSource.getBloodPressureFromDB(userId = 1)
    }
}
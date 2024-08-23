package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.remote.dashboard.OverallResponse
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

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

    override fun getLastedBodyComposition(): Flow<BodyCompositionRoom> {
        return measureRecordDataSource.getBodyCompositionFromDB(userId = 1)
    }

    override fun getLastedGlucose(): Flow<GlucoseRecordRoom> {
       return measureRecordDataSource.getGlucoseFromDB(userId = 1)
    }

    override suspend fun getCurrentWeather(): Flow<WeatherResponse?> {
        return dashBoardRemoteDataSource.getWeather()
    }

    override fun getLastedLoginUser(): Flow<LastedLoginUserRoom> {
       return measureRecordDataSource.getLastedLoginUser()
    }

    override suspend fun getAllData(): Flow<OverallResponse?> {
        return dashBoardRemoteDataSource.getAllData()
    }

}
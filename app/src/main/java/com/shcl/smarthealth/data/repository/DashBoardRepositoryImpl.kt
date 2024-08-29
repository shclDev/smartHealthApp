package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.LocalDBDataSource
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
    private val localDBDataSource: LocalDBDataSource
)  : DashBoardRepository{

    override fun getNutritionAdvice(): Flow<String> {
        return dashBoardRemoteDataSource.getNutrtionAdvice()
    }

    override fun getLastedBloodPressure(): Flow<BloodPressureRoom> {
        return localDBDataSource.getBloodPressureFromDB(userId = 1)
    }

    override fun getLastedBodyComposition(): Flow<BodyCompositionRoom> {
        return localDBDataSource.getBodyCompositionFromDB(userId = 1)
    }

    override fun getLastedGlucose(): Flow<GlucoseRecordRoom> {
       return localDBDataSource.getGlucoseFromDB(userId = 1)
    }

    override suspend fun getCurrentWeather(): Flow<WeatherResponse?> {
        return dashBoardRemoteDataSource.getWeather()
    }

    override fun getLastedLoginUser(): Flow<LastedLoginUserRoom> {
       return localDBDataSource.getLastedLoginUser()
    }

    override suspend fun getAllData(): Flow<OverallResponse?> {
        return dashBoardRemoteDataSource.getAllData()
    }

}
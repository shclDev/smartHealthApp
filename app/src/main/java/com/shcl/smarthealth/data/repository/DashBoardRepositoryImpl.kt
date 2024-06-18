package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DashBoardRepositoryImpl(
    private val dashBoardRemoteDataSource: DashBoardRemoteDataSource
)  : DashBoardRepository{
    override fun getNutritionAdvice(): Flow<String> {
        return dashBoardRemoteDataSource.getNutrtionAdvice()
    }
}
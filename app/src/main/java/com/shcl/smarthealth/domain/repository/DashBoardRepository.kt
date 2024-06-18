package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import kotlinx.coroutines.flow.Flow

interface DashBoardRepository{
    fun getNutritionAdvice() : Flow<String>
}

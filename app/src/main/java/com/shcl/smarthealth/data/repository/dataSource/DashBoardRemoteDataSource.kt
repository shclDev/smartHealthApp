package com.shcl.smarthealth.data.repository.dataSource

import kotlinx.coroutines.flow.Flow

interface DashBoardRemoteDataSource {
    fun getNutrtionAdvice() : Flow<String>
    fun getExerciseAdvice() : Flow<String>
}
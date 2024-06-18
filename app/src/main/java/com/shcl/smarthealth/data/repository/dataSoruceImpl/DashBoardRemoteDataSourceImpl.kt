package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.repository.dataSource.DashBoardRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DashBoardRemoteDataSourceImpl(private val dashBoardApi : DashBoardApi) : DashBoardRemoteDataSource{
    override fun getNutrtionAdvice(): Flow<String> {

        val msg = "영양 챙겨요!"
        return flow {
            emit(msg)
        }
    }

    override fun getExerciseAdvice(): Flow<String> {
        val msg = "운동 챙겨요!"
        return flow {
            emit(msg)
        }
    }

}
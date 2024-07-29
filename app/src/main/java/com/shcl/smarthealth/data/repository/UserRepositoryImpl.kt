package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource
) : UserRepository{

    override suspend fun signCheck(): Flow<ApiResponse<String>?> {
       return userRemoteDataSource.signCheck()
    }

    override suspend fun signUp(request: SignUpRequest): Flow<SignUpResponse> {
        return flow{
            userRemoteDataSource.signUp(request).collect{
                it?.let {
                    if(it.success){
                        it.data?.let { response->
                            emit(
                                SignUpResponse(
                                type = response.type,
                                token = response.token,
                                id = response.id,
                                name = response.name,
                                authCode = response.authCode
                            )
                            )
                        }
                    }
                }
            }
        }
        /*
        userRemoteDataSource.signUp(request)
            .map { it ->
                it?.let {
                    if (it.success) {
                        it.data?.let { response ->
                            return@map SignUpResponse(
                                type = response.type,
                                token = response.token,
                                id = response.id,
                                name = response.name,
                                authCode = response.authCode
                            )

                        }
                    }
                }
            }*/
    }

}
package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun signCheck() : Flow<ApiResponse<String>?>
    suspend fun signUp(signUpRequest: SignUpRequest) : Flow<ApiResponse<SignUpResponse>?>
}
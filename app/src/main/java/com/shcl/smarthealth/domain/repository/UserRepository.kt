package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signCheck(): Flow<ApiResponse<String>?>
    suspend fun signUp(request: SignUpRequest): Flow<SignUpResponse>?
    suspend fun userRoomUpdate(userRoom : UserRoom)


}
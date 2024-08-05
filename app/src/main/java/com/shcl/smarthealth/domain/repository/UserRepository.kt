package com.shcl.smarthealth.domain.repository


import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signCheck(): Flow<ApiResponse<String>>
    suspend fun signUp(request: SignUpRequest): Flow<SignUpResponse?>
    suspend fun userRoomUpdate(userRoom : UserRoom)
    suspend fun lastedLoginUserRoomUpdate(lastedLoginUserRoom: LastedLoginUserRoom)
    suspend fun getLastedLoginUserFromRoom() : Flow<LastedLoginUserRoom>
    suspend fun getAllUser() : Flow<List<UserRoom>>
    suspend fun signIn(request: SignInRequest) : Flow<ApiResponse<SignInResponse?>>
    suspend fun userProfile() : Flow<ProfileResponse?>

}
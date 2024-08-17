package com.shcl.smarthealth.data.repository.dataSource

import android.provider.ContactsContract.Profile
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun signCheck() : Flow<ApiResponse<String>>
    suspend fun signUp(signUpRequest: SignUpRequest) : Flow<ApiResponse<SignUpResponse>?>
    suspend fun signIn(signInRequest: SignInRequest) : Flow<ApiResponse<SignInResponse?>>
    suspend fun userProfile() : Flow<ApiResponse<ProfileResponse?>>
    suspend fun userProfilePicture() : Flow<String?>
}
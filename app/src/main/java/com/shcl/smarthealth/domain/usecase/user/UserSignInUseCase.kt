package com.shcl.smarthealth.domain.usecase.user

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSignInUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(signInRequest: SignInRequest) = repository.signIn(signInRequest)
}
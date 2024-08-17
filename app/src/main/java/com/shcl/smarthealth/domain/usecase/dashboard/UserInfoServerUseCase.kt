package com.shcl.smarthealth.domain.usecase.dashboard

import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoServerUseCase @Inject constructor(private val repository: UserRepository){

    suspend operator fun invoke() = repository.userProfile()
}
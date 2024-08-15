package com.shcl.smarthealth.domain.usecase.user

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import com.shcl.smarthealth.domain.repository.UserRepository
import javax.inject.Inject

class LastedLoginUserUseCase  @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke() = repository.getLastedLoginUserFromRoom()
}
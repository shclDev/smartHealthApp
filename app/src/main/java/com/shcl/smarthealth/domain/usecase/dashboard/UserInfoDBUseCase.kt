package com.shcl.smarthealth.domain.usecase.dashboard

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import javax.inject.Inject

class UserInfoDBUseCase @Inject constructor(private val repository: DashBoardRepository){

    operator fun invoke() = repository.getLastedLoginUser()

}
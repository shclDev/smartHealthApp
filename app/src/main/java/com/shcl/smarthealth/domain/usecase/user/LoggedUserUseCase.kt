package com.shcl.smarthealth.domain.usecase.user

import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.repository.UserRepository
import javax.inject.Inject

class LoggedUserUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke() = repository.getAllUser()
}
package com.shcl.smarthealth.domain.usecase.user

import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoggedUserUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke() : Flow<MutableList<UserRoom>?> = repository.getAllUser()
}
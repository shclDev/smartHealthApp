package com.shcl.smarthealth.domain.usecase.user


import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRoomUpdateUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(userRoom: UserRoom) = repository.userRoomUpdate(userRoom)
}
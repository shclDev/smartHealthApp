package com.shcl.smarthealth.domain.usecase.dashboard

import com.shcl.smarthealth.domain.repository.UserRepository
import javax.inject.Inject

class UserImageUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke()  = repository.userProfilePicture()
}
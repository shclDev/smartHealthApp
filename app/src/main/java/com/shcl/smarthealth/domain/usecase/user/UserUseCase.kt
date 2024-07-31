package com.shcl.smarthealth.domain.usecase.user

data class UserUseCase(
    val userSignUpUseCase: UserSignUpUseCase,
    val userRoomUpdateUseCase: UserRoomUpdateUseCase,
    val lastedLoginUserRoomUpdateUseCase: LastedLoginUserRoomUpdateUseCase,
    val userSignCheckUseCase: UserSignCheckUseCase
)

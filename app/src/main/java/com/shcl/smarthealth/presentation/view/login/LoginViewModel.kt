package com.shcl.smarthealth.presentation.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.usecase.user.UserUseCas
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCas
) : ViewModel(){

    fun requestSignUp(){
        viewModelScope.launch {
            userUseCase.userSignUpUseCase(
                SignUpRequest(name = "홍길동" ,
                nickName = null,
                birthDate = "1983-12-04",
                gender = "M",
                mobile = "010-4333-5746",
                picture = "asdf"
            )
            )
                ?.onStart {  }
                ?.onCompletion {  }
                ?.catch {  }
                ?.collect{ response->
                    response.let {

                    }?:run{

                    }
                }
        }
    }

}
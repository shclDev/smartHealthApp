package com.shcl.smarthealth.presentation.view.register


import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.utils.PreferencesManager
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.presentation.view.login.LoginStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class SignUpStatus{
    NONE , SIGNUP_SUCCESS , SIGNUP_FAILED
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private var _signUpState = MutableStateFlow(SignUpStatus.NONE)
    var signUpState = _signUpState.asStateFlow()


    fun validationUserInfo(name : String?,
                            nickName: String?,
                            birthDate : String?,
                            gender : String?,
                            mobile : String?,
                            picture : Uri?) : Boolean{
        // 이름 , 휴대폰 번호 , 생년월일, 성별 비었는지 검사
        if(name.isNullOrEmpty() && birthDate.isNullOrEmpty() && gender.isNullOrEmpty() && mobile.isNullOrEmpty() && picture.toString().isNullOrEmpty()){
            return false
        }

        return true
    }

    fun signUpStateChange(status: SignUpStatus){
        _signUpState.value = status
    }


    fun signUpUser(
        name : String,
        nickName: String,
        birthDate : String,
        gender : String,
        mobile : String,
        picture : Uri
    ) {

        viewModelScope.launch{
            userUseCase.userSignUpUseCase.invoke(SignUpRequest(
                name = name,
                nickName = nickName,
                birthDate = birthDate,
                gender = gender,
                mobile = mobile,
                picture = picture)
            )?.onStart { Log.d("register" , "signUp call") }
             ?.onCompletion {  }
                ?.catch {  }
                ?.collect {

                    it?.let {
                        if(!it.token.isNullOrEmpty() && !it.id.toString().isNullOrEmpty()){
                            //save access Token
                            PreferencesManager.saveData("accessToken" , it.token)
                            PreferencesManager.saveData("userId" , it.id)

                            userUseCase.userRoomUpdateUseCase.invoke(
                                UserRoom(
                                    userId = it.id,
                                    name = it.name,
                                    nickName = nickName,
                                    birthDate = birthDate,
                                    gender = gender,
                                    mobile = mobile,
                                    token = it.token,
                                    type = it.type,
                                    age = Utils.calcAge(birthDate),
                                    authCode = it.authCode,
                                    isFirst = true,
                                    profileUri = picture.toString(),
                                    registerTime = Utils.getCurrentDateTime()
                                )
                            )

                            /*
                            userUseCase.lastedLoginUserRoomUpdateUseCase.invoke(
                                LastedLoginUserRoom(
                                    userId = it.id,
                                    name = it.name,
                                    nickName = nickName,
                                    birthDate = birthDate,
                                    gender = gender,
                                    mobile = mobile,
                                    age = Utils.calcAge(birthDate),
                                    type = it.type,
                                    token = it.token,
                                    profileUri = picture.toString(),
                                    loginTime = Utils.getCurrentTimeStamp(),
                                    authCode = it.authCode
                                ))
                             */
                        }

                        _signUpState.value = SignUpStatus.SIGNUP_SUCCESS

                    }?:run{
                        _signUpState.value = SignUpStatus.SIGNUP_FAILED
                    }
                }
        }
    }


}
package com.shcl.smarthealth.presentation.view.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.utils.PreferencesManager
import com.shcl.smarthealth.domain.utils.Utils
import com.shcl.smarthealth.presentation.view.device.ScanDeviceState
import com.shcl.smarthealth.presentation.view.register.SignUpStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoginStatus{
    NONE , LOGIN_SUCCESS , LOGIN_FAILED
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel(){

    private val _loginState = MutableStateFlow(LoginStatus.NONE)
    val loginState = _loginState.asStateFlow()

    private val _loggedUserState = MutableStateFlow(mutableListOf<UserRoom>())
    val loggedUserState = _loggedUserState.asStateFlow()


    init {
        loggedUserCheck()
    }

    fun signCheck() {

        viewModelScope.launch {
            userUseCase.userSignCheckUseCase.invoke()
                ?.onStart { Log.d("login", "sign check call") }
                ?.onCompletion { }
                ?.catch { }
                ?.collect { response ->
                    if (response.success) {
                        _loginState.value = LoginStatus.LOGIN_SUCCESS
                    } else {
                        _loginState.value = LoginStatus.LOGIN_FAILED
                    }
                }
        }
    }

    fun signIn(mobile : String , birthDate : String) {

        viewModelScope.launch {
            userUseCase.userSignInUseCase.invoke(SignInRequest(mobile , birthDate))
                ?.onStart { Log.d("login", "signIn check call") }
                ?.onCompletion { }
                ?.catch { }
                ?.collect { response ->
                    if (response.success) {
                        response.data?.let {
                            Log.d("smarthealth" , "signIn ${response.data}")
                            PreferencesManager.saveData("accessToken", it.token)
                            _loginState.value = LoginStatus.LOGIN_SUCCESS
                        }?:run{
                            _loginState.value = LoginStatus.LOGIN_FAILED
                        }
                    } else {
                        _loginState.value = LoginStatus.LOGIN_FAILED
                    }
                }
        }
    }

    fun loginUpStateChange(status: LoginStatus){
        _loginState.value = status
    }

     fun loggedUserCheck(){

         GlobalScope.launch(Dispatchers.IO) {
            userUseCase.loggedUserUseCase.invoke()
                .onStart {   Log.d("smarthealth" , "loggedUserChk") }
                .onCompletion {  Log.d("smarthealth" , "loggedUserChk onCompletion") }
                .catch {   Log.d("smarthealth" , "loggedUserChk catch")}
                .collect{
                    it.let {
                        Log.d("smarthealth" , "loggedUser size : ${it.size}")
                        _loggedUserState.value = it.toMutableList()
                    }
                }
        }
    }

    fun lastedUserLoginUpdate(user : UserRoom){

        viewModelScope.launch{

            userUseCase.lastedLoginUserRoomUpdateUseCase.invoke(
                LastedLoginUserRoom(
                    userId = user.userId,
                    name = user.name,
                    nickName = user.nickName,
                    birthDate = user.birthDate,
                    gender = user.gender,
                    mobile = user.mobile,
                    age = Utils.calcAge(user.birthDate),
                    type = user.type,
                    token = user.token,
                    profileUri = user.profileUri,
                    loginTime = Utils.getCurrentTimeStamp(),
                    authCode = user.authCode
                )
            )
        }
    }
}
package com.shcl.smarthealth.presentation.view.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.data.db.LocalDBDao
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
import kotlin.math.log

enum class LoginStatus{
    NONE , LOGIN_SUCCESS , LOGIN_SUCCESS_AFTER_TUTORIAL , LOGIN_FAILED
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val localDBDao: LocalDBDao
) : ViewModel(){

    private val _loginState = MutableStateFlow(LoginStatus.NONE)
    val loginState = _loginState.asStateFlow()

    private val _loggedUserState = MutableStateFlow(mutableListOf<UserRoom>())
    val loggedUserState = _loggedUserState.asStateFlow()

    var loggedInUserId = 0


    init {
        loggedUserCheck()
    }

    fun isTutorialCompleted(userId : Int?){

        var isCompleted = true

        var id = userId ?: loggedInUserId

        viewModelScope.launch(Dispatchers.IO) {
            localDBDao.getTutorialByUserID(id).collect{
                it?.let {
                    if(it.complete){
                        _loginState.value = LoginStatus.LOGIN_SUCCESS
                    }else{
                        _loginState.value = LoginStatus.LOGIN_SUCCESS_AFTER_TUTORIAL
                    }
                }?:run{
                    _loginState.value = LoginStatus.LOGIN_SUCCESS
                }

            }
        }

        //return isCompleted
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

                            loggedInUserId = it.id
                            PreferencesManager.saveData("userId" , it.id)
                            PreferencesManager.saveData("accessToken", it.token)

                            userUseCase.userRoomUpdateUseCase.invoke(
                                UserRoom(
                                    userId = it.id,
                                    name = it.name,
                                    nickName = "",
                                    birthDate = birthDate,
                                    gender = "",
                                    mobile = mobile,
                                    token = it.token,
                                    type = it.type,
                                    age = Utils.calcAge(birthDate),
                                    authCode = it.authCode,
                                    isFirst = true,
                                    profileUri = "",
                                    registerTime = Utils.getCurrentDateTime()
                                )
                            )
                            isTutorialCompleted(it.id)
                            //_loginState.value = LoginStatus.LOGIN_SUCCESS
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
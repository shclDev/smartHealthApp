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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {


    fun validationUserInfo(name : String,
                            nickName: String,
                            birthDate : String,
                            gender : String,
                            mobile : String,
                            picture : Uri) : Boolean{
        // 이름 , 휴대폰 번호 , 생년월일, 성별 비었는지 검사
        if(name.isNullOrEmpty() || birthDate.isNullOrEmpty() || gender.isNullOrEmpty() || mobile.isNullOrEmpty() || picture.toString().isNullOrEmpty()){
            return false
        }

        return true
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
                    if(!it.token.isNullOrEmpty()){
                        //save access Token
                        PreferencesManager.saveData("accessToken" , it.token)

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
                    }
                }
        }
    }


}
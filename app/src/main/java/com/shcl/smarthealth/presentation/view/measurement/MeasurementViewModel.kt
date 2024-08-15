package com.shcl.smarthealth.presentation.view.measurement

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.LastedLoginUserRoom
import com.shcl.smarthealth.domain.model.db.UserRoom
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.usecase.isens.IsensScanDeviceUseCase
import com.shcl.smarthealth.domain.usecase.omron.OmronDeviceUseCase
import com.shcl.smarthealth.domain.usecase.user.UserUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
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

@HiltViewModel
class MeasurementViewModel @Inject constructor(
    private val voiceUseCase: VoiceUseCase,
    private val userUseCase: UserUseCase,
    private val omronDeviceUseCase: OmronDeviceUseCase,
    private val isensDeviceUseCase : IsensScanDeviceUseCase
) : ViewModel() {

    private val _loggedUserState = MutableStateFlow<LastedLoginUserRoom?>(null)
    val loggedUserState = _loggedUserState.asStateFlow()

    init {


    }

    fun getLastedLoginUser(){

        viewModelScope.launch{
            userUseCase.lastedLoginUserUseCase.invoke()
                .onStart {   Log.d("smarthealth" , "loggedUserChk") }
                .onCompletion {  Log.d("smarthealth" , "loggedUserChk onCompletion") }
                .catch {   Log.d("smarthealth" , "loggedUserChk catch")}
                .collect{
                    it.let {
                        Log.d("smarthealth" , "loggedUser : $it")
                        _loggedUserState.value = it
                    }
                }
        }
    }


    fun clovaVoice(text : String){

        viewModelScope.launch {
            voiceUseCase.voiceTTSUseCase.invoke(
                spearker = "nara",
                text = text)
                .onStart {   Log.d("smarthealth" , "survey start") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it?.let {
                        voiceUseCase.voicePlayUseCase.invoke(it)
                    }
                }
        }
    }


}
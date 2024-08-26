package com.shcl.smarthealth.presentation.view.introduce

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.data.repository.dataSoruceImpl.RecognizerStatus
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IntroduceViewModel @Inject constructor(
    private val voiceUseCase: VoiceUseCase,
) : ViewModel()
{

    private var _micVisible = MutableStateFlow<Boolean>(true)
    var micVisible = _micVisible.asStateFlow()

    private var _voiceMessage = MutableStateFlow<String>("")
    var voiceMessage = _voiceMessage.asStateFlow()

    private var _recognizeMessage = MutableStateFlow<String>("")
    var recognizeMessage = _recognizeMessage.asStateFlow()


    init {
        //speechStatus()
    }

    fun speech(){
        /*
        viewModelScope.launch {
            voiceUseCase.voiceSTTUseCase.invoke()

            }*/
        viewModelScope.launch {
            voiceUseCase.voiceSTTUseCase.recognizer()
                .onStart {

                }.onCompletion {  }
                .collect{
                    Log.d("speech" , "${it.status}")
                    when(it.status){
                        RecognizerStatus.RESULT->{
                            _micVisible.value = true
                            _recognizeMessage.value = it.message
                        }
                        RecognizerStatus.READY_FOR_SPEECH->{
                            _micVisible.value = true
                        }
                        RecognizerStatus.BEGINNING_SPEECH->{}
                        RecognizerStatus.INIT->{}
                    }
                }
        }
    }

    fun speechStatus(){
        viewModelScope.launch {
            voiceUseCase.voiceSTTUseCase.recognizer()
                .onStart {

                }.onCompletion {  }
                .collect{
                    Log.d("speech" , "${it.status}")
                    when(it.status){
                        RecognizerStatus.RESULT->{
                            _micVisible.value = true
                            _recognizeMessage.value = it.message
                        }
                        RecognizerStatus.READY_FOR_SPEECH->{
                            _micVisible.value = true
                        }
                        RecognizerStatus.BEGINNING_SPEECH->{}
                        RecognizerStatus.INIT->{}
                    }
                }
        }
    }
}


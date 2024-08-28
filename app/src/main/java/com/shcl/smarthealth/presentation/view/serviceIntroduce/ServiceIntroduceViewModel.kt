package com.shcl.smarthealth.presentation.view.serviceIntroduce

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.R
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.repository.dataSoruceImpl.RecognizerStatus
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import com.shcl.smarthealth.presentation.view.introduce.IntrouduceAssistant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ServiceIntroduceViewModel @Inject constructor(
    private val voiceUseCase: VoiceUseCase,
) : ViewModel()
{

    private var _micVisible = MutableStateFlow<Boolean>(false)
    var micVisible = _micVisible.asStateFlow()

    private var _recognizerVisible = MutableStateFlow<Boolean>(false)
    var recognizerVisible = _recognizerVisible.asStateFlow()

    private var _voiceMessage = MutableStateFlow<String>("")
    var voiceMessage = _voiceMessage.asStateFlow()

    private var _recognizeMessage = MutableStateFlow<String>("")
    var recognizeMessage = _recognizeMessage.asStateFlow()

    private var _allDone = MutableStateFlow<Boolean>(false)
    var allDone = _allDone.asStateFlow()

    private var _assistant : ServiceIntrouduceAssistant? = null
    private var _questionId = 1

    val MAX_QUESTION_ID = 8

    private lateinit var _timer : CountDownTimer

    init {
         assistant()
    }

    fun recognizerAfterNextPage(){
        _timer = object : CountDownTimer(1000 , 1000){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                assistant()
            }
        }
        _timer.start()
    }

    fun recognizer(){

        viewModelScope.launch {
            voiceUseCase.voiceSTTUseCase.recognizer()
                .onStart {

                }.onCompletion {  }
                .collect{
                    Log.d("speech" , "${it.status}")
                    when(it.status){
                        RecognizerStatus.RESULT->{
                            _recognizerVisible.value = false
                            _micVisible.value = true
                            _recognizeMessage.value = it.message
                            _questionId += 1

                            recognizerAfterNextPage()
                        }
                        RecognizerStatus.READY_FOR_SPEECH->{
                            _recognizeMessage.value = ""
                            //띠링 소리 삽입
                            val media = MediaPlayer.create(GlobalVariables.context , R.raw.dding)
                            media.start()
                            //_recognizerVisible.value = true
                            _micVisible.value = true
                        }
                        RecognizerStatus.BEGINNING_SPEECH->{
                            _recognizerVisible.value = true
                        }
                        RecognizerStatus.INIT->{}
                        RecognizerStatus.ERROR->{
                            _recognizerVisible.value = false
                            _voiceMessage.value = it.message
                        }
                    }
                }
        }
    }

    fun assistant(){

        if(_questionId > MAX_QUESTION_ID){
            _allDone.value = true
            return
        }

        _assistant = ServiceIntrouduceAssistant.getQuestionIdByAssistant(_questionId)

        _assistant?.let {
            if(it.autoNextPlay){
                _micVisible.value = false
            }else{
                recognizer()
                _micVisible.value = true
            }
            playVoice(it.title , it.autoNextPlay)
        }
    }

    fun playVoice(voice : String? , nextPlay : Boolean){
        _recognizeMessage.value = ""

        voice?.let {

            _voiceMessage.value = it

            viewModelScope.launch {
                voiceUseCase.voiceTTSUseCase.invoke("nara" , it ).collect{ path->
                    path?.let {

                        voiceUseCase.voicePlayUseCase.invoke(it).collect{ complete->
                            // 음성 답변 필요 없이 진행하는 케이스
                            if(complete && nextPlay){
                                _questionId += 1
                                assistant()
                            }
                        }
                    }
                }
            }
        }
    }
}


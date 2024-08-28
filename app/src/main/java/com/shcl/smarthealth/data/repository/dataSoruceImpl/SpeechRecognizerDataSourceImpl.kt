package com.shcl.smarthealth.data.repository.dataSoruceImpl


import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.repository.dataSource.SpeechRecognizerSource
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.model.remote.introduce.RecognizerState
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


enum class RecognizerStatus{
    INIT , READY_FOR_SPEECH , BEGINNING_SPEECH , RESULT , ERROR
}

class SpeechRecognizerDataSourceImpl  @Inject constructor(

) : SpeechRecognizerSource{

    lateinit var recognizer : SpeechRecognizer
    //val CLIENT_ID = "tdjdr9pruq"

    /*
    override fun speech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"com.shcl.smarthealth")
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , "ko-KR")

        recognizer = SpeechRecognizer.createSpeechRecognizer(GlobalVariables.context)
        recognizer.setRecognitionListener(recognitionListener)
        recognizer.startListening(intent)
    }*/

    override fun speechRecognizer(recognizerState: RecognizerState?) : Flow<RecognizerState> = callbackFlow{

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"com.shcl.smarthealth")
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE , "ko-KR")

        recognizer = SpeechRecognizer.createSpeechRecognizer(GlobalVariables.context)
        recognizer.setRecognitionListener(object : RecognitionListener {
        // 말하기 시작할 준비가되면 호출
        override fun onReadyForSpeech(params: Bundle) {
            //Toast.makeText(GlobalVariables.context, "음성 준비완료", Toast.LENGTH_SHORT).show()

            trySend(RecognizerState(
                status = RecognizerStatus.READY_FOR_SPEECH,
                code = "",
                message = ""
            ))
            /*
            speechRecognizer(RecognizerState(
                status = RecognizerStatus.READY_FOR_SPEECH,
                code = "",
                message = ""
            ))*/

            Log.d("speech" , "onReadyForSpeech")
            //ready()
            // binding.tvState.text = "이제 말씀하세요!"
        }
        // 말하기 시작했을 때 호출
        override fun onBeginningOfSpeech() {
            Toast.makeText(GlobalVariables.context, "음성 인식 중.", Toast.LENGTH_SHORT).show()

            trySend(RecognizerState(
                status = RecognizerStatus.BEGINNING_SPEECH,
                code = "",
                message = ""
            ))

            /*
            speechRecognizer(RecognizerState(
                status = RecognizerStatus.BEGINNING_SPEECH,
                code = "",
                message = ""
            ))*/
            Log.d("speech" , "onBeginningOfSpeech")
            //binding.tvState.text = "잘 듣고 있어요."
        }
        // 입력받는 소리의 크기를 알려줌
        override fun onRmsChanged(rmsdB: Float) {}
        // 말을 시작하고 인식이 된 단어를 buffer에 담음
        override fun onBufferReceived(buffer: ByteArray) {}
        // 말하기를 중지하면 호출
        override fun onEndOfSpeech() {
            // binding.tvState.text = "끝!"
        }
        // 오류 발생했을 때 호출
        override fun onError(error: Int) {
            val message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_CLIENT -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_NETWORK -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_NO_MATCH -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_SERVER -> "다시 말씀해주시겠어요?"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과을 초과하였습니다.다시 말씀해주시겠어요?"
                else -> "알 수 없는 오류입니다."
            }

            trySend(RecognizerState(
                status = RecognizerStatus.ERROR,
                code = "",
                message = message
            ))

            //binding.tvState.text = "에러 발생: $message"
        }
        // 인식 결과가 준비되면 호출
        override fun onResults(results: Bundle) {
            // 말을 하면 ArrayList에 단어를 넣고 textView에 단어를 이어줌
            var matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

            Log.d("speech" , "onResults")
            Log.d("speech" , matches.toString())

            var message = matches.toString().replace("[" ,"'")
            message = message.replace("]","'")

            trySend(RecognizerState(
                status = RecognizerStatus.RESULT,
                code = "",
                message = message
            ))
            /*
            matches?.let {
                it.removeFirst()
                it.removeLast()

                trySend(RecognizerState(
                    status = RecognizerStatus.RESULT,
                    code = "",
                    message = matches.toString()
                ))
            }*/
        }
        // 부분 인식 결과를 사용할 수 있을 때 호출
        override fun onPartialResults(partialResults: Bundle) {}
        // 향후 이벤트를 추가하기 위해 예약
        override fun onEvent(eventType: Int, params: Bundle) {}
    })


        recognizer.startListening(intent)
        /*
            recognizerState?.let {
                trySend(recognizerState)
            }*/
            awaitClose()
    }


    override fun ready() : Flow<RecognizerState>{
        return callbackFlow {
            trySend(RecognizerState(
                status = RecognizerStatus.READY_FOR_SPEECH,
                code = "",
                message = ""
            ))
        }
    }


    override fun audioRecording(speech: Array<Short>) {
        TODO("Not yet implemented")
    }

    override fun partialResult(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun finalResult(result: ArrayList<String?>?) :  Flow<RecognizerState>{
        return callbackFlow {
            trySend(RecognizerState(
                status = RecognizerStatus.RESULT,
                code = "",
                message = result.toString()
            ))
        }
    }

    override fun recognitionError(): Flow<RecognizerState> {
        TODO("Not yet implemented")
    }

    override fun clientInactive() {
        TODO("Not yet implemented")
    }

    override fun endPointDetectTypeSelected() {
        TODO("Not yet implemented")
    }


}
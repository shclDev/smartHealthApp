package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.data.repository.dataSoruceImpl.RecognizerStatus
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.model.omron.RequestType
import com.shcl.smarthealth.domain.model.remote.introduce.RecognizerState
import com.shcl.smarthealth.presentation.view.device.MeasurementRecordState
import kotlinx.coroutines.flow.Flow

interface SpeechRecognizerSource {
    fun speech()
    fun speechRecognizer(recognizerState: RecognizerState?) : Flow<RecognizerState>
    fun ready() : Flow<RecognizerState>
    fun audioRecording(speech : Array<Short> )
    fun partialResult() : Flow<String>
    fun finalResult(result : ArrayList<String?>?) : Flow<RecognizerState>
    fun recognitionError() : Flow<RecognizerState>
    fun clientInactive()
    fun endPointDetectTypeSelected()
    //fun pairing(discoveredDevice: DiscoveredDevice) : Flow<MeasurementRecordState>

}
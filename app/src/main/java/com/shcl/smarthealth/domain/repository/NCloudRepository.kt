package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.data.repository.dataSoruceImpl.RecognizerStatus
import com.shcl.smarthealth.domain.model.remote.introduce.RecognizerState
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import kotlinx.coroutines.flow.Flow

interface NCloudRepository {

    suspend fun clovaVoice(speaker : String , text:String) : Flow<String?>
    suspend fun voiceToText() : Flow<RecognizerState>
}
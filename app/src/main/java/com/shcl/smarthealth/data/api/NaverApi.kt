package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NaverApi {
    @POST("tts-premium/v1/tts")
    suspend fun clovaVoice(
        @Body voiceRequest: VoiceRequest) : Response<String>
}
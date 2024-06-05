package com.shcl.smarthealth.data.remote

import retrofit2.http.Header
import retrofit2.http.POST

interface NaverApiService {
    @POST("/tts-premium/v1/tts")
    suspend fun clovaVoice() : String


}
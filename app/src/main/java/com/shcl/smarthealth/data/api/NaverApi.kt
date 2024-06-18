package com.shcl.smarthealth.data.api

import retrofit2.http.POST

interface NaverApi {
    @POST("/tts-premium/v1/tts")
    suspend fun clovaVoice() : String


}
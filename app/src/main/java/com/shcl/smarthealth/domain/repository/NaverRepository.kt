package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import retrofit2.Response

interface NaverRepository {

    suspend fun clovaVoice(speaker : String , text : String)
}
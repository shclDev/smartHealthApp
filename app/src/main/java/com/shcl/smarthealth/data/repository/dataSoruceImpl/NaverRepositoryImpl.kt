package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.api.NaverApi
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.repository.NaverRepository
import retrofit2.Response
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val api : NaverApi
) : NaverRepository{

    override suspend fun clovaVoice(voiceRequest: VoiceRequest) : Response<String> {
        return api.clovaVoice(voiceRequest)
    }
}
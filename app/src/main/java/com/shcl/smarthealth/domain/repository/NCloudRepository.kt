package com.shcl.smarthealth.domain.repository

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest

interface NCloudRepository {

    suspend fun clovaVoice(voiceRequest: VoiceRequest)


}
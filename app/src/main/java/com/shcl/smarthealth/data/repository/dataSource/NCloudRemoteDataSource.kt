package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest

interface NCloudRemoteDataSource {

    suspend fun clovaVoice(voiceRequest: VoiceRequest)

}
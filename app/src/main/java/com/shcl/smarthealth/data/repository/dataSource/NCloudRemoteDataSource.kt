package com.shcl.smarthealth.data.repository.dataSource

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import kotlinx.coroutines.flow.Flow

interface NCloudRemoteDataSource {

    suspend fun clovaVoice(speaker : String , text : String) : Flow<String?>

}
package com.shcl.smarthealth.domain.usecase.voice

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.repository.NCloudRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VoiceTTSUseCase @Inject constructor(private val repositroy : NCloudRepository){
    suspend operator fun invoke(spearker:String , text:String) : Flow<String?> = repositroy.clovaVoice(spearker , text)
}

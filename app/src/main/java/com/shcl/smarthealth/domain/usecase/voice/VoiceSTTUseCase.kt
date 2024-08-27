package com.shcl.smarthealth.domain.usecase.voice

import com.shcl.smarthealth.domain.repository.NCloudRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VoiceSTTUseCase @Inject constructor(private val repositroy : NCloudRepository){
    suspend fun recognizer() = repositroy.voiceToText()
}
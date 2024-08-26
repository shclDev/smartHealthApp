package com.shcl.smarthealth.domain.usecase.voice

data class VoiceUseCase(
    val voiceTTSUseCase: VoiceTTSUseCase,
    val voicePlayUseCase: VoicePlayUseCase,
    val voiceSTTUseCase: VoiceSTTUseCase
)


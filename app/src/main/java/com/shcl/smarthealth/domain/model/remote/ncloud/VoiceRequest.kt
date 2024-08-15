package com.shcl.smarthealth.domain.model.remote.ncloud

data class VoiceRequest(
    val speaker : String,
    val text : String,
    val volume : Integer?,
    val speed : Integer,
    val emotion : Integer?,
    val emotionStrength : Integer?,
    val format : String?,
    val samplingRate : Integer?,
    val alpha : Integer?,
    val endPitch : Integer?
)

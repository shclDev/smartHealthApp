package com.shcl.smarthealth.domain.model.remote.introduce

import com.shcl.smarthealth.data.repository.dataSoruceImpl.RecognizerStatus

data class RecognizerState (
    val status : RecognizerStatus,
    val code : String,
    val message : String,
)

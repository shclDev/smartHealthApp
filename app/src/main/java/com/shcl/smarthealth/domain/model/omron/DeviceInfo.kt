package com.shcl.smarthealth.domain.model.omron

data class DeviceInfo(
    val address : String,
    val localName : String,
    val completeLocalName : String,
    val modelName : String,
    val deviceCategory : String,
    val protocol : String,
    val userIndex : Int,
    val consentCode : Int,
    val sequenceNumberOfLatestRecord : Int,
    val numberOfRecords : Int,
    val databaseChangeIncrement : Long,
    val userDataUpdateFlag : Boolean
)

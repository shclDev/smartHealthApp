package com.shcl.smarthealth.domain.repository

interface IsensRepository {

    fun requestTimeSync()
    fun requestAllRecords()
    fun requestRecordAfterSequence(sequence : Int)
    fun connect(address : String)
    fun disconnectDevice()
    fun unPairDevice(address : String)
    fun destorySDK()


}
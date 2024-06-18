package com.shcl.smarthealth.domain.model.omron

import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQMeasurementRecordKey
import jp.co.ohq.ble.enumerate.OHQSessionOptionKey
import jp.co.ohq.ble.enumerate.OHQUserDataKey

data class SessionData(
    val option : Map<OHQSessionOptionKey, Any>,
    val deviceCategory : OHQDeviceCategory,
    val modelName : String,
    val currentTime : String,
    val batteryLevel : Int,
    val userIndex : Int,
    val userData : Map<OHQUserDataKey , Any>,
    val databaseChangeIncrement : Long,
    val sequenceNumberOfLatestRecord : Int,
    val measurementRecord : List<Map<OHQMeasurementRecordKey , Any>>,
    val completionReason : OHQCompletionReason
)
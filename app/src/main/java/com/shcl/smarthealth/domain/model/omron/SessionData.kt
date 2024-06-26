package com.shcl.smarthealth.domain.model.omron

import jp.co.ohq.ble.enumerate.OHQCompletionReason
import jp.co.ohq.ble.enumerate.OHQDeviceCategory
import jp.co.ohq.ble.enumerate.OHQMeasurementRecordKey
import jp.co.ohq.ble.enumerate.OHQSessionOptionKey
import jp.co.ohq.ble.enumerate.OHQUserDataKey

data class SessionData(
    var option : Map<OHQSessionOptionKey, Any>? = mapOf(),
    var deviceCategory : OHQDeviceCategory? = OHQDeviceCategory.Unknown,
    var modelName : String? = "",
    var currentTime : String? = "",
    var batteryLevel : Int? = Int.MIN_VALUE,
    var userIndex : Int? = Int.MIN_VALUE,
    var userData : Map<OHQUserDataKey , Any>? = mapOf(),
    var databaseChangeIncrement : Long? = Long.MIN_VALUE,
    var sequenceNumberOfLatestRecord : Int? = Int.MIN_VALUE,
    var measurementRecord : List<Map<OHQMeasurementRecordKey , Any>>? = listOf(),
    var completionReason : OHQCompletionReason? = OHQCompletionReason.Canceled
)
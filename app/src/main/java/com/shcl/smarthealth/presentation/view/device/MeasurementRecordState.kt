package com.shcl.smarthealth.presentation.view.device

import com.shcl.smarthealth.domain.model.omron.SessionData
import jp.co.ohq.ble.enumerate.OHQDeviceCategory

data class MeasurementRecordState (
    var sessionData : SessionData?,
    var category : OHQDeviceCategory? = OHQDeviceCategory.Unknown,
    var status: MeasurementStatus? = MeasurementStatus.Unknown,
    var errorMsg : String? = ""
)


enum class MeasurementStatus {
    Success,
    Fail,
    InitComplete,
    NoData,
    Unknown,
    Disconnected ,
    Connection ,
    Connected ,
    Disconnecting ,
    Cancel,
    ParingSuccess,
    ParingFail,
    Error
}
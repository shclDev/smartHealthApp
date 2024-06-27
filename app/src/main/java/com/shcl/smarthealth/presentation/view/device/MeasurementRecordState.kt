package com.shcl.smarthealth.presentation.view.device

import com.shcl.smarthealth.domain.model.omron.SessionData

data class MeasurementRecordState (
    var sessionData : SessionData?,
    var status: MeasurementStatus? = MeasurementStatus.Unknown,
    var errorMsg : String? = ""
)


enum class MeasurementStatus {
    Success,
    Fail,
    Unknown,
    Disconnected ,
    Connection ,
    Connected ,
    Disconnecting ,
    Cancel,
    Error
}
package com.shcl.smarthealth.presentation.view.device

import android.util.SparseArray
import com.isens.standard.ble.IBLE_GlucoseRecord

data class IsensGlucoseRecordState (
    var status : MeasurementStatus? = MeasurementStatus.Unknown,
    var errorMsg : String="",
    var records : SparseArray<IBLE_GlucoseRecord>?
)
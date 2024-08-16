package com.shcl.smarthealth.presentation.view.measurement.content

interface MeasurementBase {
    fun content()
    fun displayTime(displayTimeMs : Long)
    fun voicePlay(text : String)
}
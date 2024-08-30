package com.shcl.smarthealth.presentation.view.analysis.component

sealed class Page {
    object HealthAge : Page()
    object TotalGuide : Page()
    object DetailGuide : Page()
    object RiskInfo : Page()
}
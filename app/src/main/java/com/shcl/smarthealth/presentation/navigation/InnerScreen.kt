package com.shcl.smarthealth.presentation.navigation

import com.shcl.smarthealth.R

sealed  class InnerScreen(val route : String) {

    object dashboard : OuterScreen((R.string.dashboard_screen).toString())
    object deviceScan : OuterScreen((R.string.device_scan).toString())
    object home : OuterScreen((R.string.home_screen).toString())

}
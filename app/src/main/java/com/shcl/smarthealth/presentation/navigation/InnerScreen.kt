package com.shcl.smarthealth.presentation.navigation

import com.shcl.smarthealth.R

sealed  class InnerScreen(val route : String) {

    object dashboard : InnerScreen((R.string.dashboard_screen).toString())
    object deviceScan : InnerScreen((R.string.device_scan).toString())
//    object home : InnerScreen((R.string.home_screen).toString())
    object analysis : InnerScreen((R.string.analysis_screen).toString())
    object setting : InnerScreen((R.string.setting_screen).toString())
    object challenge : InnerScreen((R.string.challenge_screen).toString())
    object reservation : InnerScreen((R.string.reservation_screen).toString())
    //object survey : InnerScreen(("survey").toString())

}
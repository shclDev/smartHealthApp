package com.shcl.smarthealth.presentation.navigation

import com.shcl.smarthealth.R

sealed  class OuterScreen(val route : String) {

    object intro : OuterScreen((R.string.intro_screen).toString())
    object login : OuterScreen((R.string.login_screen).toString())
    object home : OuterScreen((R.string.home_screen).toString())
    object registser : OuterScreen(("register").toString())
    object terms : OuterScreen(("terms").toString())
    object registerComplete : OuterScreen(("registerComplete").toString())
    object suvery : OuterScreen(("survey").toString())
    object measurement : OuterScreen(("measurement").toString())


}
package com.shcl.smarthealth.presentation.navigation

sealed  class Screen(val route : String) {

    object intro : Screen("intro_screen")
    object dashboard : Screen("dashboard")

}
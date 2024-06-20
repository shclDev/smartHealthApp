package com.shcl.smarthealth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardScreen
import com.shcl.smarthealth.presentation.view.device.ScanDeviceScreen
import com.shcl.smarthealth.presentation.view.intro.IntroScreen
import com.shcl.smarthealth.presentation.view.login.LoginScreen

@Composable
fun NavGraph(navCtrl : NavHostController) {

    NavHost(
        navController = navCtrl,
        startDestination = Screen.intro.route
    ){
        composable(route = Screen.intro.route ){
            IntroScreen(nav = navCtrl , modifier = null)
        }

        composable(route = Screen.dashboard.route){
            DashBoardScreen(nav = navCtrl)
        }

        composable(route = Screen.deviceScan.route){
            ScanDeviceScreen(nav = navCtrl)
        }

        composable(route = Screen.login.route){
            LoginScreen(nav = navCtrl)
        }

    }



}
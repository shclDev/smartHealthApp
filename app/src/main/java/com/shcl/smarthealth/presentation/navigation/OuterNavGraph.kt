package com.shcl.smarthealth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shcl.smarthealth.presentation.view.home.DismissibleNavigationDrawer
import com.shcl.smarthealth.presentation.view.intro.IntroScreen
import com.shcl.smarthealth.presentation.view.login.LoginScreen
import com.shcl.smarthealth.presentation.view.register.RegisterScreen

@Composable
fun OuterNavGraph(navCtrl : NavHostController) {

    NavHost(
        navController = navCtrl,
        startDestination = OuterScreen.intro.route
    ){
        composable(route = OuterScreen.intro.route ){
            IntroScreen(nav = navCtrl , modifier = null)
        }

        composable(route = OuterScreen.login.route){
            LoginScreen(nav = navCtrl)
        }

        composable(route = OuterScreen.home.route){
            DismissibleNavigationDrawer()
        }

        composable(route = OuterScreen.registser.route){
            RegisterScreen()
        }


    }
}

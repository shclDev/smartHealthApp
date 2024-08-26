package com.shcl.smarthealth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shcl.smarthealth.presentation.view.home.DismissibleNavigationDrawer
import com.shcl.smarthealth.presentation.view.intro.IntroScreen
import com.shcl.smarthealth.presentation.view.introduce.IntroduceScreen
import com.shcl.smarthealth.presentation.view.login.LoginScreen
import com.shcl.smarthealth.presentation.view.measurement.MeasurementScreen
import com.shcl.smarthealth.presentation.view.register.RegisterCompleteScreen
import com.shcl.smarthealth.presentation.view.register.RegisterScreen
import com.shcl.smarthealth.presentation.view.register.TermsOfUseScreen
import com.shcl.smarthealth.presentation.view.survey.SurveyScreen

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
            RegisterScreen(nav = navCtrl)
        }

        composable(route = OuterScreen.terms.route){
            TermsOfUseScreen(nav = navCtrl)
        }

        composable(route = OuterScreen.registerComplete.route){
            RegisterCompleteScreen(nav = navCtrl)
        }

        composable(route = OuterScreen.survery.route){
            SurveyScreen(nav = navCtrl)
        }

        composable(route = OuterScreen.measurement.route){
            MeasurementScreen(nav =  navCtrl)
        }

        composable(route = OuterScreen.introduce.route){
            IntroduceScreen(nav = navCtrl)
        }

    }
}


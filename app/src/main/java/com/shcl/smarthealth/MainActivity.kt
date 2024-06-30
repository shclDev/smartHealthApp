package com.shcl.smarthealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shcl.smarthealth.common.GlobalVariables.context
import com.shcl.smarthealth.presentation.navigation.OuterNavGraph
import com.shcl.smarthealth.presentation.view.home.DismissibleNavigationDrawer
import com.shcl.smarthealth.ui.theme.SmartHealthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        context = applicationContext;

        setContent {
            SmartHealthTheme {
                navController = rememberNavController()
                OuterNavGraph(navCtrl = navController)
            }
        }
    }


}
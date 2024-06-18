package com.shcl.smarthealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shcl.smarthealth.presentation.navigation.NavGraph
import com.shcl.smarthealth.ui.theme.SmartHealthTheme
import dagger.hilt.android.AndroidEntryPoint
import jp.co.ohq.ble.OHQDeviceManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartHealthTheme {
                navController = rememberNavController()
                NavGraph(navCtrl = navController)
            }
        }
    }


}
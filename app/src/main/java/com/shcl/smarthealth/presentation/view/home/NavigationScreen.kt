package com.shcl.smarthealth.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.presentation.navigation.InnerScreen
import com.shcl.smarthealth.presentation.view.analysis.AnalysisScreen
import com.shcl.smarthealth.presentation.view.challenge.ChallengeScreen
import com.shcl.smarthealth.presentation.view.dashboard.DashBoardScreen
import com.shcl.smarthealth.presentation.view.device.ScanDeviceScreen
import com.shcl.smarthealth.presentation.view.reservation.ReservationScreen
import com.shcl.smarthealth.presentation.view.setting.SettingScreen
import com.shcl.smarthealth.ui.theme.Color143F91
import com.shcl.smarthealth.ui.theme.Color757575
import com.shcl.smarthealth.ui.theme.ColorD49E1
import com.shcl.smarthealth.ui.theme.Typography

@Composable
fun DismissibleNavigationDrawer(){

    val menus = listOf(
        MenuItem(
            id="dashboard",
            title="대시보드",
            contentDesc = "대시보드",
            image = Icons.Default.Menu,
            route = InnerScreen.dashboard.route
        ),
        MenuItem(
            id="analysis",
            title="분석결과",
            contentDesc = "분석결과",
            image = Icons.Default.Send,
            route = InnerScreen.analysis.route
        ),
        MenuItem(
            id="reservation",
            title="예약",
            contentDesc = "예약",
            image = Icons.Default.Check,
            route = InnerScreen.reservation.route
        ),
        MenuItem(
            id="setting",
            title="설정",
            contentDesc = "설정",
            image = Icons.Default.Settings,
            route = InnerScreen.setting.route
        ),
        MenuItem(
            id="challenge",
            title="도전",
            contentDesc = "도전",
            image = Icons.Default.Face,
            route = InnerScreen.challenge.route
        ))

    val drawerState = rememberDrawerState(DrawerValue.Open)
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val nav : NavHostController = rememberNavController()

    DismissibleNavigationDrawer(
        modifier = Modifier.background(color = Color.White),
        drawerState = drawerState,
        drawerContent = {
            DismissibleDrawerSheet {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    DrawerHeader()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = ColorD49E1)
                            .padding(vertical = 40.dp)
                    )
                    menus.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.image, contentDescription = item.contentDesc) },
                            label = { Text(
                                text = item.title.substringAfterLast("."),
                                style = Typography.labelLarge,
                                color = if(selectedIndex == index) Color143F91 else Color757575
                                ) },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                nav.navigate(route = item.route)
                            },
                            modifier = Modifier.padding(horizontal = 40f.pxToDp() , vertical = 40f.pxToDp())
                        )
                    }
                }
            }
        },
        content = {
            NavHost(
                navController = nav,
                startDestination = InnerScreen.dashboard.route
            ){

                composable(route = InnerScreen.dashboard.route){
                    DashBoardScreen(nav = nav)
                }

                /*
                composable(route = InnerScreen.deviceScan.route){
                    AnalysisScreen(nav = nav)
                }*/

                composable(route = InnerScreen.analysis.route){
                    AnalysisScreen(nav = nav)
                }

                composable(route = InnerScreen.setting.route){
                    //SettingScreen(nav = nav)
                    ScanDeviceScreen(nav = nav)
                }

                composable(route = InnerScreen.challenge.route){
                    ChallengeScreen(nav = nav)
                }
                
                composable(route = InnerScreen.reservation.route){
                    ReservationScreen(nav = nav)
                }
        }
    }
    )
}
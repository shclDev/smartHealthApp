package com.shcl.smarthealth.presentation.view.home


import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id:String,
    val title : String,
    val contentDesc : String,
    val image : ImageVector,
    val route : String =""
)

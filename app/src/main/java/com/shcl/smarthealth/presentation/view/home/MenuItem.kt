package com.shcl.smarthealth.presentation.view.home


import androidx.compose.ui.graphics.painter.Painter

data class MenuItem(
    val id:String,
    val title: String,
    val contentDesc: String,
    val image: Painter,
    val selectedImage : Painter,
    val route: String =""
)

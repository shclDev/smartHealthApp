package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.BackGroundColor
import com.shcl.smarthealth.ui.theme.ColorD4D9E1

@Composable
fun VerticalLine(width : Int) {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width.pxToDp())
            .background(ColorD4D9E1)
        //.gradientBackground(BackGroundColor, angle = 90f)
    )

}
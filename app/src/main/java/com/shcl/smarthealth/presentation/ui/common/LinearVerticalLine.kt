package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.ui.theme.BackGroundColor

@Composable
fun LinearVerticalLine() {

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(60f.pxToDp())
            .background(brush = Brush.verticalGradient(BackGroundColor))
        //.gradientBackground(BackGroundColor, angle = 90f)
    )

}
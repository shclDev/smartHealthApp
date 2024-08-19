package com.shcl.smarthealth.presentation.ui.common

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.utils.pxToDp
import com.shcl.smarthealth.domain.utils.pxToSp

@Composable
fun AnimtaionText(
    modifier : Modifier? = null,
    fontSize : Float,
    fontColor : Color,
    enter : ()->Unit? = {},
    exit :  ()->Unit? = {},
    text : String,
    style : TextStyle,
    isVisible : Boolean = false
    ){

    var visible by remember { mutableStateOf(isVisible) }

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            // Start the slide from 40 (pixels) above where the content is supposed to go, to
            // produce a parallax effect
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + scaleIn(
            // Animate scale from 0f to 1f using the top center as the pivot point.
            transformOrigin = TransformOrigin(0.5f, 0f)
        ) + fadeIn(initialAlpha = 0.3f , animationSpec = tween(2000, easing = LinearEasing)),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(animationSpec = tween(2000, easing = LinearEasing)) + scaleOut(targetScale = 1.2f)
        ) {
        Text(text= text , fontSize = fontSize.pxToSp() , style = style , color = fontColor , fontWeight = FontWeight.W700)
    }
}
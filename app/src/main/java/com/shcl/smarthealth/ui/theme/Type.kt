package com.shcl.smarthealth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.R



val notoSanskr = FontFamily(
    Font(R.font.notosanskr_bold , FontWeight.Bold , FontStyle.Normal),
    Font(R.font.notosanskr_medium , FontWeight.Medium , FontStyle.Normal),
    Font(R.font.notosanskr_regular , FontWeight.Normal , FontStyle.Normal),
    Font(R.font.notosanskr_lght , FontWeight.Light , FontStyle.Normal),
    Font(R.font.notosanskr_thin , FontWeight.Thin , FontStyle.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.W700,
        fontSize = 48.sp,
        lineHeight = 150.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.W700,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = notoSanskr,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 28.sp,
    )
)

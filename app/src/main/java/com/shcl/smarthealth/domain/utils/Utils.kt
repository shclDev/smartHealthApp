package com.shcl.smarthealth.domain.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import java.text.SimpleDateFormat
import java.util.Date

object Utils {

    fun Float.pxToDp(context: Context): Float = (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    fun Int.dpToPx(context: Context): Float = (this * context.resources.displayMetrics.density)
    @Composable
    fun Float.dp() = with(LocalDensity.current) {  Dp(this@dp).toSp() }

    @Composable
    fun Int.dp() = with(LocalDensity.current) {  Dp(this@dp.toFloat()).toSp()  }

    fun getTimeStamp() : String {
        val dateformat : SimpleDateFormat = SimpleDateFormat("yyyyMMddhhmmss")
        return dateformat.format(Date())
    }

}
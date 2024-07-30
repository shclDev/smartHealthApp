package com.shcl.smarthealth.domain.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.common.GlobalVariables
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    fun convertTimeStampToDate(timeStamp : Int) : String?{
        try{
            val formatter = SimpleDateFormat("yyyy.MM.dd")
            val netDate = Date(timeStamp.toLong() * 1000)
            return formatter.format(netDate)
        }catch (e : Exception){
            Log.e("smartHealth" , e.message.toString())
            return null
        }
    }

    fun createTempFileFromUri(context : Context, uri : Uri, fileName : String) : File?{
        return try {
            val stream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile(fileName, "", context.cacheDir)
            org.apache.commons.io.FileUtils.copyInputStreamToFile(stream,file)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun uriFromFilePath(uri : Uri) : String?{
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = GlobalVariables.context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }
        return null
    }

    fun getCurrentDate() : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.E")
        return current.format(formatter)
    }

    fun getCurrentDateTime() : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.hh.mm")
        return current.format(formatter)
    }

    fun getCurrentTime() : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("a hh:mm")
        return current.format(formatter)
    }

    @Composable
    fun pxTsp(size : Float) : TextUnit {
        return (size / LocalDensity.current.fontScale).sp
    }

}
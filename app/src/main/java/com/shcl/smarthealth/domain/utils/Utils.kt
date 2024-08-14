package com.shcl.smarthealth.domain.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.model.remote.survey.Question
import com.shcl.smarthealth.domain.model.remote.survey.answer.Answer
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.concurrent.TimeUnit


object Utils {

    fun Float.pxToDp(context: Context): Float = (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
    fun Int.dpToPx(context: Context): Float = (this * context.resources.displayMetrics.density)
    @Composable
    fun Float.dp() = with(LocalDensity.current) {  Dp(this@dp).toSp() }

    @Composable
    fun Int.dp() = with(LocalDensity.current) {  Dp(this@dp.toFloat()).toSp()  }


    fun getAnswer(questionId : Int , questions : List<Question>?) : Answer? {

        val question = questions?.find { it.questionId == questionId }

        question?.let { it ->
            return Answer(
                questionId = it.questionId,
                answerType = it.answerType,
                answer = ""
            )
        } ?: run { return null }
    }

    fun getHistoryAnswer(questions: List<Question>?, diseaseName : String, answerType : String ): Answer?{

        val question = questions?.find { it.content.compareTo(diseaseName) == 0 && it.answerType.compareTo(answerType) == 0 }
        question?.let {
            return Answer(questionId =  it.questionId , answerType = it.answerType , answer = "")
        } ?: run { return null}

    }

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

    fun writeResponseBodyToDisk(body : ResponseBody){

    }

    fun uriFromFilePath(uri : Uri) : String?{
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = GlobalVariables.context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToNext())
            {
                //val path = it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
                val path = cursor.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                //val tempUri = Uri.fromFile(File(path))

                cursor.close()
                return path
            }
        }
        return null
    }

    fun uriToBitmap(uri : Uri) : Bitmap?{

        var bitmap : Bitmap? = null

        try{

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(GlobalVariables.context.contentResolver , uri))
            }else{
                bitmap = MediaStore.Images.Media.getBitmap(GlobalVariables.context.contentResolver , uri)
            }

            return  bitmap

        }catch (e : Exception){
            Log.e("smarthealth" , "${e.message}")
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
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm")
        return current.format(formatter)
    }

    fun getCurrentTimeStamp() : Long{
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    }

    fun getCurrentTime() : String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("a hh:mm")
        return current.format(formatter)
    }

    fun birthDateToServer(birthdate : String) : String{
        Log.d("smarthealth","19"+birthdate.substring(0,2) + "-" + birthdate.substring(2,4) +"-" + birthdate.substring(4,6))
        return "19"+birthdate.substring(0,2) + "-" + birthdate.substring(2,4) +"-" + birthdate.substring(4,6)
    }

    fun mobileToServer(mobile : String) : String{
        return mobile.substring(0,3) + "-" + mobile.substring(3,6) +"-" + mobile.substring(6,mobile.length)
    }

    @Composable
    fun pxTsp(size : Float) : TextUnit {
        return (size / LocalDensity.current.fontScale).sp
    }

    fun convertGender(gender : String) : String{
        return when(gender){
            "F"->"여성"
            "M"->"남성"
            else->"성별 없음"
        }
    }

    fun calcAge(birthdate: String) : Int{
        val current = LocalDateTime.now()
        val formatterYear  = DateTimeFormatter.ofPattern("YYYY")

        try{
            val birthYear = ("19"+birthdate.substring(0,2)).toInt()
            val currentYear =  current.format(formatterYear).toInt()

            return currentYear - birthYear
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return 0
    }

    fun calcProfileAge(birthdate: String) : Int{
        val current = LocalDateTime.now()
        val formatterYear  = DateTimeFormatter.ofPattern("YYYY")

        try{
            val birthYear = birthdate.substring(0,4).toInt()
            val currentYear =  current.format(formatterYear).toInt()

            return currentYear - birthYear
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return 0
    }

    fun hourMinFormat(hour : String? , min : String?) : String?{

        try{
            if( !hour.isNullOrEmpty() && !min.isNullOrEmpty()){
                return "$hour:$min"
            }else{
                return null
            }
        }catch(e : Exception){
            Log.e("smarthealth" , e.message.toString() )
        }

        return null
    }

    fun makeYearRange(startYear : Int , endYear : Int) : ArrayList<String>{
        var years : ArrayList<String> = arrayListOf()

        for(i in startYear until endYear){
            years.add(i.toString())
        }

        return years
    }

    fun makeAgeRange(startAge : Int , endAge : Int) : ArrayList<String>{
        var ages : ArrayList<String> = arrayListOf()

        for(i in startAge until endAge){
            ages.add(i.toString())
        }

        return ages
    }

}
package com.shcl.smarthealth.domain.utils

import android.content.Context
import android.content.SharedPreferences
import com.shcl.smarthealth.common.GlobalVariables

object PreferencesManager {

    private val sharedPreferences : SharedPreferences = GlobalVariables.context.getSharedPreferences("shcl" , Context.MODE_PRIVATE)


    fun saveData(key : String , value : String){
        val editor = sharedPreferences.edit()
        editor.putString(key , value)
        editor.apply()
    }

    fun saveData(key: String , value :Int){
        val editor = sharedPreferences.edit()
        editor.putInt(key , value)
        editor.apply()
    }

    fun getData(key : String , defalutValue : String) : String{
        return sharedPreferences.getString(key , defalutValue) ?: defalutValue
    }

    fun getUserId(key : String , defalutValue: Int) : Int{
        return sharedPreferences.getInt(key , defalutValue) ?: defalutValue
    }
}
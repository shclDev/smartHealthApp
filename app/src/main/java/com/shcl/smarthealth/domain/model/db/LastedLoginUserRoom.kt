package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "lasted_login_user_tb")
data class LastedLoginUserRoom (
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("nickName")
    val nickName : String,
    @SerializedName("birthDate")
    val birthDate : String,
    @SerializedName("age")
    val age : Int = 0,
    @SerializedName("gender")
    val gender : String,
    @SerializedName("mobile")
    val mobile : String,
    @SerializedName("token")
    val token : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("profile")
    val profileUri : String,
    @SerializedName("authCode")
    val authCode : String,
    @SerializedName("loginTime")
    val loginTime : Long

)
package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_tb")
data class UserRoom(
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
    @SerializedName("gender")
    val gender : String,
    @SerializedName("mobile")
    val mobile : String,
    @SerializedName("token")
    val token : String,
    @SerializedName("registerTime")
    val registerTime : String
)
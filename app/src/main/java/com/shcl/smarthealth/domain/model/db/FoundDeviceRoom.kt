package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "found_device_tb")
data class FoundDeviceRoom(
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("address")
    val address : String?,
    @SerializedName("deviceCategory")
    val deviceCategory : String,
    @SerializedName("rssi")
    val rssi : Int?,
    @SerializedName("modelName")
    val modelName : String?,
    @SerializedName("localName")
    val localName : String?,
    @SerializedName("isUse")
    val isUse : Boolean = true,
    @SerializedName("timeStamp")
    val timeStamp : String
) : Serializable

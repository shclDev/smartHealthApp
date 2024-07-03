package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "bloodPressure")
data class BloodPressureRoom (
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("user_id")
    val userId : Int,
    @SerializedName("diastolic")
    val diastolic : Float,
    @SerializedName("diastolicUnit")
    val diastolicUnit : String,
    // val meanArterial : Float,
    @SerializedName("timeStamp")
    val timeStamp : String,
    @SerializedName("pulseRate")
    val pulseRate : Float,
    @SerializedName("systolic")
    val systolic : Float,
    @SerializedName("systolicUnit")
    val systolicUnit : String
) : Serializable
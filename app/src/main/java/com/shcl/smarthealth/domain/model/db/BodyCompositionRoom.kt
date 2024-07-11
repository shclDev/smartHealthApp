package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "body_composition_tb")
data class BodyCompositionRoom (
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("userIdx")
    val userIndex : Int,
    @SerializedName("sequenceNumber")
    val sequenceNumber : Int,
    // val meanArterial : Float,
    @SerializedName("weight")
    val weight : Float,
    @SerializedName("weightUnit")
    val weightUnit : String,
    @SerializedName("fatPercentage")
    val fatPercentage : Float,
    @SerializedName("bodyAge")
    val bodyAge : Int,
    @SerializedName("bmi")
    val bmi : Float,
    @SerializedName("musclePercentage")
    val musclePercentage : Float,
    @SerializedName("bodyFatPercentage")
    val bodyFatPercentage : Float,
    @SerializedName("skeletalMusclePercentage")
    val skeletalMusclePercentage : Float,
    @SerializedName("timestamp")
    val timeStamp : String
    ) : Serializable
package com.shcl.smarthealth.domain.model.db

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class HealthCheckUpRoom(
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("food_calcium")
    val calcium : Int,
    @SerializedName("food_protein")
    val protein : Int,
    @SerializedName("sleep_quality")
    val quality : Int,
    @SerializedName("sleep_bedtime")
    val bedTime : String,
    @SerializedName("sleep_wakeup")
    val wakeUpTime : String,
    @SerializedName("sleep_insomnia")
    val insomnia : String,
    @SerializedName("depression_interest")
    val interest : Int,
    @SerializedName("depression_self_deprecation")
    val selfDeprecation : Int,
    @SerializedName("smoking_check")
    val smokingCheck : Int,
    @SerializedName("smoking_quit_year")
    val quitSmokingYear : String,
    @SerializedName("smoking_quit_month")
    val quitSmokingMonth : String,
    @SerializedName("smoking_avg_cnt")
    val smokingAvgCnt : Int,
    @SerializedName("drinking_avg")
    val drinkingAvg : String
)

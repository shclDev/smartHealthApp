package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tutorial_tb")
data class TutorialRoom(
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("complete")
    val complete : Boolean,
    @SerializedName("registerTime")
    val registerTime : String
)
package com.shcl.smarthealth.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "glucose_record_tb")
data class GlucoseRecordRoom(
    @PrimaryKey(autoGenerate = true)
    var pk : Long = 0,
    @SerializedName("userId")
    var userId : Int,
    @SerializedName("glucoseData")
    var glucoseData : Double,
    @SerializedName("flag_cs")
    var flagCs : Int = 0, // Console:1 none:0
    @SerializedName("flag_hilow")
    var flagHilow: Int = 0, // high:1 low:-1 normal:0 ketonelow:-2
    @SerializedName("flag_context")
    var flagContext: Int = 0,
    @SerializedName("flag_meal")
    var flagMeal: Int = 0, // before: -1, after:1 none:0
    @SerializedName("flag_fasting")
    var flagFasting: Int = 0, //fasting:1 none:0
    @SerializedName("flag_ketone")
    var flagKetone: Int = 0,//ketone:1 glucose:0
    @SerializedName("flag_nomark")
    var flagNomark: Int = 0,//nomark:1 none:0
    @SerializedName("timeoffset")
    var timeOffset: Int = 0, //device time offset
    @SerializedName("time")
    var time: Long = 0L
)

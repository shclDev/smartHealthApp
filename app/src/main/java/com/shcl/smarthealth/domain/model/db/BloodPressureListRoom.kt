package com.shcl.smarthealth.domain.model.db

import com.google.gson.annotations.SerializedName

data class BloodPressureListRoom(
    @SerializedName("page")
    val page: Int =1,
    @SerializedName("results")
    val bloodPressures : List<BloodPressureRoom>
)

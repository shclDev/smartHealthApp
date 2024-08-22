package com.shcl.smarthealth.domain.model.remote.dashboard

data class BloodPressureCommentInfo(
    val comment: String,
    val commentType: String,
    val commentTypeName: String,
    val id: Int,
    val videoUrl: String
)
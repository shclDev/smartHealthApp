package com.shcl.smarthealth.domain.model.remote.weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
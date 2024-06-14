package com.shcl.smarthealth.data.ble

import jp.co.ohq.ble.enumerate.OHQGender


data class UserInfo(
    val name : String,
    val dateOfBirth : String,
    val height : Float,
    val gender : String = OHQGender.Male.name
)
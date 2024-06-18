package com.shcl.smarthealth.domain.model.omron

import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import jp.co.ohq.ble.enumerate.OHQDeviceCategory

data class DiscoveredDevice(
    val address : String,
    val advertisementData : List<ADStructure>,
    val deviceCategory : OHQDeviceCategory,
    val rssi : Int,
    val modelName : String,
    val localName : String
)

package com.shcl.smarthealth.domain.model.omron

import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import jp.co.ohq.ble.enumerate.OHQDeviceCategory

data class DiscoveredDevice(
    var address : String,
    var advertisementData : List<ADStructure>? = listOf(),
    var deviceCategory : OHQDeviceCategory? = OHQDeviceCategory.Unknown,
    var rssi : Int = Int.MIN_VALUE,
    var modelName : String? = "",
    var localName : String? = ""
)

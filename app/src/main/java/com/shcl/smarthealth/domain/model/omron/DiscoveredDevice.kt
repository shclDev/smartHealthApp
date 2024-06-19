package com.shcl.smarthealth.domain.model.omron

import com.neovisionaries.bluetooth.ble.advertising.ADStructure
import jp.co.ohq.ble.enumerate.OHQDeviceCategory

data class DiscoveredDevice(
    var address : String,
    var advertisementData : List<ADStructure>?= null,
    var deviceCategory : OHQDeviceCategory? = null,
    var rssi : Int? = null,
    var modelName : String? = null,
    var localName : String? = null
)

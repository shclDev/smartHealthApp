package com.shcl.smarthealth.common

import android.content.Context
import kotlinx.coroutines.CoroutineScope


enum class Host{
    DEV , PROD
}

object GlobalVariables {
    lateinit var context : Context
    var mHost : Host = Host.PROD;

    //const val shclProdBaseUrl = "http://192.168.219.112:8000"
    const val shclProdBaseUrl = "http://192.168.0.79:8000"
    const val naverProdBaseUrl = "https://naveropenapi.apigw.ntruss.com/";
    const val naverDevBaseUrl = "https://naveropenapi.apigw.ntruss.com/";
    const val weatherBaseUrl = "https://api.openweathermap.org/"
    const val weatherApiKey = "6a52ad393f34aad788d110071d4cd7ac"

    fun getNaverHost() : String{
        if(mHost == Host.PROD){
            return naverProdBaseUrl;
        }else{
            return naverDevBaseUrl;
        }
    }

    lateinit var coroutineScope: CoroutineScope

}
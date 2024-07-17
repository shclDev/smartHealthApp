package com.shcl.smarthealth.common

import android.content.Context
import kotlinx.coroutines.CoroutineScope


enum class Host{
    DEV , PROD
}

object GlobalVariables {
    lateinit var context : Context
    var mHost : Host = Host.PROD;

    const val naverProdBaseUrl = "https://naveropenapi.apigw.ntruss.com/";
    const val naverDevBaseUrl = "https://naveropenapi.apigw.ntruss.com/";

    fun getNaverHost() : String{
        if(mHost == Host.PROD){
            return naverProdBaseUrl;
        }else{
            return naverDevBaseUrl;
        }
    }

    lateinit var coroutineScope: CoroutineScope

}
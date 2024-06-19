package com.shcl.smarthealth.common

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext


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

}
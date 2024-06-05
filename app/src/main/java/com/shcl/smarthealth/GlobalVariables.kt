package com.shcl.smarthealth


enum class Host{
    DEV , PROD
}

object GlobalVariables {
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
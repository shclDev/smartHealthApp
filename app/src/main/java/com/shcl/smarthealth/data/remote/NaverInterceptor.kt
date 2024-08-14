package com.shcl.smarthealth.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class NaverInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain){

        val newRequest = request().newBuilder()
            .addHeader("X-NCP-APIGW-API-KEY-ID", "tdjdr9pruq")
            .addHeader("X-NCP-APIGW-API-KEY" , "XAY4fMhyhDfDjw31v83nmZjHU1rf0i9FINshMFZg")
            .build()

        proceed(newRequest)

    }
}
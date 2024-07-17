package com.shcl.smarthealth.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class NaverInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain){

        val newRequest = request().newBuilder()
            .addHeader("X-NCP-APIGW-API-KEY-ID", "qra66du7co")
            .addHeader("X-NCP-APIGW-API-KEY" , "946TlwB0xORmWboI7jOz9ehrG30vDwPInjXrU2Mj")
            .build()

        proceed(newRequest)

    }
}
package com.shcl.smarthealth.data.remote

import android.util.Log
import com.shcl.smarthealth.domain.utils.PreferencesManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import kotlin.jvm.Throws

class AuthInterceptor : Interceptor{

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if(chain.request().headers["Auth"] == "false"){
            val newRequest = chain.request().newBuilder()
                .removeHeader("Auth")
                .build()

            return chain.proceed(newRequest)
        }

        var token = ""
        runBlocking {
            token = PreferencesManager.getData("accessToken" , "")
        }

        val newRequest = chain.request().newBuilder()
            .addHeader("authorization","Bearer ${token}")
            .build()

        val response = chain.proceed(newRequest)
        return response
    }
}
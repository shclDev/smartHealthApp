package com.shcl.smarthealth.data.remote

import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.NaverApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val naverApiInstance : NaverApi by lazy{
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder : OkHttpClient.Builder = client.newBuilder()
            .addInterceptor(NaverInterceptor())
            .addInterceptor(interceptor as HttpLoggingInterceptor)
        val retrofit = Retrofit.Builder().baseUrl(GlobalVariables.getNaverHost())
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        retrofit.create(NaverApi::class.java)
    }

}
package com.shcl.smarthealth.data.remote

import com.shcl.smarthealth.GlobalVariables
import com.shcl.smarthealth.domain.api.NaverApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val naverApiInstance : NaverApiService by lazy{
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder : OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor as HttpLoggingInterceptor)
        val retrofit = Retrofit.Builder().baseUrl(GlobalVariables.getNaverHost())
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        retrofit.create(NaverApiService::class.java)
    }

}
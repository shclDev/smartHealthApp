package com.shcl.smarthealth.di

import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class weather

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class shcl


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    /*
    @Provides
    @Singleton
    fun provideNaverRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVariables.naverProdBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    @Provides
    @Singleton
    @weather
    fun provideWeatherRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVariables.weatherBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @shcl
    fun provideShclRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVariables.shclProdBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @shcl
    fun provideDashBoardApi(@shcl retrofit: Retrofit): DashBoardApi {
        return retrofit.create(DashBoardApi::class.java)
    }

    @Provides
    @Singleton
    @weather
    fun provideWeatherApi(@weather retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    @shcl
    fun provideUserApi(@shcl retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }


}
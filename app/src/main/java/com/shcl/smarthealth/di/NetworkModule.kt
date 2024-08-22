package com.shcl.smarthealth.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.common.GlobalVariables.context
import com.shcl.smarthealth.data.api.DashBoardApi
import com.shcl.smarthealth.data.api.MeasurementApi
import com.shcl.smarthealth.data.api.NaverApi
import com.shcl.smarthealth.data.api.SurveyApi
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.api.WeatherApi
import com.shcl.smarthealth.data.remote.AuthInterceptor
import com.shcl.smarthealth.data.remote.NaverInterceptor
import com.shcl.smarthealth.domain.utils.LocalTimeConvert
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.LocalTime
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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ncloud

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ncloudHttp


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        // http-logging
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @ncloudHttp
    fun provideNcloudHttpClient(): OkHttpClient {

        // http-logging
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NaverInterceptor())
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
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
    @ncloud
    fun provideNCloudRetrofitInstance(@ncloudHttp okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(GlobalVariables.naverProdBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @shcl
    fun provideShclRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder().setDateFormat("HH:mm").registerTypeAdapter(LocalTime::class.java , LocalTimeConvert()).create()

        return Retrofit.Builder()
            .baseUrl(GlobalVariables.shclProdBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    @Provides
    @Singleton
    @shcl
    fun provideSurveyApi(@shcl retrofit: Retrofit): SurveyApi {
        return retrofit.create(SurveyApi::class.java)
    }

    @Provides
    @Singleton
    @ncloud
    fun provideNaverApi(@ncloud retrofit: Retrofit): NaverApi {
        return retrofit.create(NaverApi::class.java)
    }

    @Provides
    @Singleton
    @shcl
    fun provideMeasurementApi(@shcl retrofit: Retrofit): MeasurementApi {
        return retrofit.create(MeasurementApi::class.java)
    }


}
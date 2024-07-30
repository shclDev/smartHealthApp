package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserApi {
    @GET("/signCheck")
    suspend fun signCheck(
    ) : Response<ApiResponse<String>>

    /*
    @Multipart
    @POST("/signUp")
    suspend fun signUp(
       @Part("name") name : RequestBody,
       @Part("nickName") nickName : RequestBody?,
       @Part("birthDate") birthDate : RequestBody,
       @Part("gender") gender : RequestBody,
       @Part("mobile") mobile : RequestBody,
       @Part picture : MultipartBody.Part
    ) : Response<ApiResponse<SignUpResponse>>*/

    @Multipart
    @POST("/signUp")
    suspend fun signUp(
        @Part("data") data: RequestBody,
        @Part picture: MultipartBody.Part
    ) : Response<ApiResponse<SignUpResponse>>

}
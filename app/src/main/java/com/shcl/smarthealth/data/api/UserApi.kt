package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserApi {
    @GET("/signCheck")
    suspend fun signCheck(
    ) : Response<ApiResponse<String>>

    @Multipart
    @POST("/signUp")
    suspend fun signUp(
        @Part("data") data: RequestBody,
        @Part picture: MultipartBody.Part
    ) : Response<ApiResponse<SignUpResponse>>

    @POST("/signIn")
    @Headers("Content-Type: application/json")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ) : Response<ApiResponse<SignInResponse?>>

    @GET("/personal/profile/")
    suspend fun profile(
    ) : Response<ApiResponse<ProfileResponse?>>

    @GET("/personal/profile/picture")
    suspend fun profilePicture(
    ) : Response<ResponseBody>


}
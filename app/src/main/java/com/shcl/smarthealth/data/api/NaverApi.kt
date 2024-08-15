package com.shcl.smarthealth.data.api

import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface NaverApi {
    @FormUrlEncoded
    @POST("tts-premium/v1/tts")
    suspend fun clovaVoice(
       @Field("speaker") speaker:String ,
       @Field("text") text : String) : Response<ResponseBody>
}
package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import androidx.core.net.toFile
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserRemoteDataSourceImpl (
    @NetworkModule.shcl private val userApi : UserApi
) : UserRemoteDataSource{
    override suspend fun signCheck(): Flow<ApiResponse<String>?>{
        try{
            val response = userApi.signCheck()

            if(response.isSuccessful){
                response.body()?.let{
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smartHealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                success = false,
                code = "",
                message = "",
                data = null
            )
            )
        }
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): Flow<ApiResponse<SignUpResponse>?> {
        try{

            val imageFile : File = signUpRequest.picture.toFile()
            val imagePart = imageFile.asRequestBody("image/*".toMediaTypeOrNull())

            val response = userApi.signUp(
               name = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.name),
               nickName = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.nickName.orEmpty()),
               birthDate = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.birthDate),
               gender = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.gender),
               mobile = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.mobile),
               picture = MultipartBody.Part.createFormData("picture", imageFile.name , imagePart),
            )

            if(response.isSuccessful){
                response.body()?.let{
                    return flow{
                        emit(it)
                    }
                }
            }else{
                return flow{
                    emit(
                        ApiResponse(
                        success = false,
                        code = response.code().toString(),
                        message = response.message(),
                        data = null
                    )
                    )
                }
            }
        }catch (e : Exception){
            Log.e("smartHealth" , e.message.toString())
        }

        return flow{
            emit(
                ApiResponse(
                success = false,
                code = "",
                message = "",
                data = null
            )
            )
        }
    }

}
package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import com.google.gson.Gson
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.utils.Utils
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

            Log.d("register", "userRemoteDataSourceImpl signUp")

            //val file = Utils.createTempFileFromUri(context , signUpRequest.picture , signUpRequest.picture.path.toString() )
            //val fileBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), signUpRequest.picture.toFile().absolutePath)
            //val filePart : MultipartBody.Part = MultipartBody.Part.createFormData("picture" , "picture.jpeg" , fileBody)
//            val imageFile : File = signUpRequest.picture.toFile()
            //val imagePart = file?.asRequestBody("image/*".toMediaTypeOrNull())

            val file = File(Utils.uriFromFilePath(signUpRequest.picture))
            val imageRequestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("picture", file.name, imageRequestBody)

            /*
            val response = userApi.signUp(
                data = RequestBody.create("application/json".toMediaTypeOrNull(), signUpRequest.toString()),
               name = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.name.orEmpty()),
               nickName = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.nickName.orEmpty()),
               birthDate = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.birthDate),
               gender = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.gender),
               mobile = RequestBody.create("text/plain".toMediaTypeOrNull(), signUpRequest.mobile),
               picture = imagePart
            )*/

            var gson = Gson()
            signUpRequest.birthDate="1983-12-04"
            signUpRequest.mobile="010-4333-5746"
            var jsonStr = gson.toJson(signUpRequest)
            val response = userApi.signUp(
                data = RequestBody.create("application/json".toMediaTypeOrNull(), jsonStr),
                picture = imagePart
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
            Log.e("register" , e.message.toString())
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
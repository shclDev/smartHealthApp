package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.UserApi
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.common.ApiResponse
import com.shcl.smarthealth.domain.model.remote.user.ProfileResponse
import com.shcl.smarthealth.domain.model.remote.user.SignInRequest
import com.shcl.smarthealth.domain.model.remote.user.SignInResponse
import com.shcl.smarthealth.domain.model.remote.user.SignUpRequest
import com.shcl.smarthealth.domain.model.remote.user.SignUpResponse
import com.shcl.smarthealth.domain.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import org.apache.commons.io.IOUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.Date


class UserRemoteDataSourceImpl (
    @NetworkModule.shcl private val userApi : UserApi
) : UserRemoteDataSource{
    override suspend fun signCheck(): Flow<ApiResponse<String>>{
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

            val file = File(Utils.uriFromFilePath(signUpRequest.picture))
            val imageRequestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("picture", file.name, imageRequestBody)

            signUpRequest.birthDate = Utils.birthDateToServer(signUpRequest.birthDate)
            signUpRequest.mobile = Utils.mobileToServer(signUpRequest.mobile)

            var gson = Gson()
            var jsonStr = gson.toJson(signUpRequest)

            val response = userApi.signUp(
                data = RequestBody.create("application/json".toMediaTypeOrNull(), jsonStr),
                picture = imagePart
            )

            if(response.isSuccessful){

                Log.d("register" , "${response.body()}")

                return flow{
                    emit(response.body())
                }

                response.body()?.let{
                    return flow{
                        emit(response.body())
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

    override suspend fun signIn(signInRequest: SignInRequest): Flow<ApiResponse<SignInResponse?>> {
        try{
            val response = userApi.signIn(signInRequest)


            if(response.isSuccessful){

                response.body()?.let {
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
            Log.e("smarthealth",e.message.toString())
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

    override suspend fun userProfile(): Flow<ApiResponse<ProfileResponse?>> {
        try{
            val response = userApi.profile()

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

    override suspend fun userProfilePicture(): Flow<String?> {

        try{
            val response = userApi.profilePicture()

            if(response.isSuccessful){
                val responseBody : ResponseBody = response.body()!!

                val istream : InputStream = responseBody.byteStream()
                var read = 0
                var bytes = ByteArray(1024)

                val tempName : String = Date().time.toString()
                val f = File("${GlobalVariables.context.cacheDir}","${tempName}.jpeg")

                f.createNewFile()
                val outputStream = FileOutputStream(f)

                while ((istream.read(bytes).also { read = it }) != -1) {
                    outputStream.write(bytes, 0, read)
                }

                Log.d("smarthealth" , f.path)

                istream.close()

                return flow{
                    emit(f.path)
                }
            }
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

        return flow{
            emit(null)
        }

    }

}
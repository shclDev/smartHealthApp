package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.media.MediaPlayer
import android.os.Environment
import android.util.Log
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.data.api.NaverApi
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.Date


class NCloudRemoteDataSourceImpl(
    @NetworkModule.ncloud private val naverApi: NaverApi
) : NCloudRemoteDataSource{


    override suspend fun clovaVoice(speaker : String , text : String) : Flow<String?> {
        try{
            val response = naverApi.clovaVoice(speaker , text)

            if(response.isSuccessful){

                val responseBody : ResponseBody = response.body()!!

                val istream : InputStream = responseBody.byteStream()
                var read = 0
                var bytes = ByteArray(1024)

                val tempName : String = Date().time.toString()
                val f = File("${GlobalVariables.context.cacheDir}","${tempName}.mp3")

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
        }catch(e : Exception){
            Log.e("smarthealth", e.message.toString())
        }

        return flow{
            emit(null)
        }


    }

}
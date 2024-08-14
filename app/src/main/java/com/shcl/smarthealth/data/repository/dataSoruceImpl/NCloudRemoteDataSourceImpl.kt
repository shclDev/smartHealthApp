package com.shcl.smarthealth.data.repository.dataSoruceImpl

import android.util.Log
import com.shcl.smarthealth.data.api.NaverApi
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.di.NetworkModule
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest



class NCloudRemoteDataSourceImpl(
    @NetworkModule.ncloud private val naverApi: NaverApi
) : NCloudRemoteDataSource{


    override suspend fun clovaVoice(voiceRequest: VoiceRequest) {
        try{
            val response = naverApi.clovaVoice(voiceRequest)

            if(response.isSuccessful){

                val bodyString = response.body()!!.toString()
                Log.d("smarthealth" , bodyString)

                /*
                val input : InputStream = getInputStream()
                var read = 0
                val bytes = ByteArray(1024)

                // 랜덤한 이름으로 mp3 파일 생성
                val tempname: String = java.lang.Long.valueOf(Date().getTime()).toString()
                val f: File = File("$tempname.mp3")

                f.createNewFile()
                val outputStream: OutputStream = FileOutputStream(f)

                while ((input.read(bytes).also { read = it }) != -1) {
                    outputStream.write(bytes, 0, read)
                }
                input.close()*/
            }
        }catch(e : Exception){
            Log.e("smarthealth", e.message.toString())
        }
    }

}
package com.shcl.smarthealth.domain.usecase.voice

import android.media.MediaPlayer
import android.provider.MediaStore.Audio.Media
import android.util.Log

class VoicePlayUseCase private constructor(){

    companion object {
        private var mediaPlayer : MediaPlayer = MediaPlayer()
        val INSTANCE: VoicePlayUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { VoicePlayUseCase() }
    }

    operator fun invoke(path : String)  {
            try{

                if(mediaPlayer.isPlaying){
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }

                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(path)
                mediaPlayer.prepare()
                mediaPlayer.start()

            }catch (e : Exception){
                Log.e("smarthealth" , e.message.toString())
            }
    }
}
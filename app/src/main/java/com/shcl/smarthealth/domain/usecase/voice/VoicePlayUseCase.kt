package com.shcl.smarthealth.domain.usecase.voice

import android.media.MediaPlayer
import android.util.Log

class VoicePlayUseCase {
    operator fun invoke(path : String)  {
        try{
            val mediaPlayer : MediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(path)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }catch (e : Exception){
            Log.e("smarthealth" , e.message.toString())
        }

    }
}
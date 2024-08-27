package com.shcl.smarthealth.domain.usecase.voice

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.provider.MediaStore.Audio.Media
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class VoicePlayUseCase private constructor(){

    companion object {
        private var mediaPlayer : MediaPlayer = MediaPlayer()
        val INSTANCE: VoicePlayUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { VoicePlayUseCase() }
    }

    operator fun invoke(path : String)  : Flow<Boolean> {
        return callbackFlow {
            try {

                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }

                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(path)
                mediaPlayer.prepare()
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener(
                    object : MediaPlayer.OnCompletionListener {
                        override fun onCompletion(mp: MediaPlayer?) {
                            trySend(true)
                        }
                    })

            } catch (e: Exception) {
                Log.e("smarthealth", e.message.toString())
            }

            awaitClose{
                mediaPlayer.stop()
                mediaPlayer.release()
            }
        }
    }
}
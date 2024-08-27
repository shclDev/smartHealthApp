package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.SpeechRecognizerSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.introduce.RecognizerState
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.repository.NCloudRepository
import kotlinx.coroutines.flow.Flow

class NCloudRepositoryImpl(
    private val nCloudRemoteDataSource: NCloudRemoteDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource,
    private val speechRecognizerSource: SpeechRecognizerSource
) : NCloudRepository {

    override suspend fun clovaVoice(speaker : String , text : String) : Flow<String?> {
        return nCloudRemoteDataSource.clovaVoice(speaker , text )
    }

    override suspend fun voiceToText()  : Flow<RecognizerState>{
        return speechRecognizerSource.speechRecognizer(null)
    }




}
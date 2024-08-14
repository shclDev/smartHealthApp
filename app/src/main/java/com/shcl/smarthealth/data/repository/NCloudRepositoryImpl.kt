package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.repository.dataSource.MeasureRecordDataSource
import com.shcl.smarthealth.data.repository.dataSource.NCloudRemoteDataSource
import com.shcl.smarthealth.data.repository.dataSource.UserRemoteDataSource
import com.shcl.smarthealth.domain.model.remote.ncloud.VoiceRequest
import com.shcl.smarthealth.domain.repository.NCloudRepository

class NCloudRepositoryImpl(
    private val nCloudRemoteDataSource: NCloudRemoteDataSource,
    private val measureRecordDataSource: MeasureRecordDataSource
) : NCloudRepository {

    override suspend fun clovaVoice(voiceRequest: VoiceRequest) {
        return nCloudRemoteDataSource.clovaVoice(voiceRequest)
    }

}
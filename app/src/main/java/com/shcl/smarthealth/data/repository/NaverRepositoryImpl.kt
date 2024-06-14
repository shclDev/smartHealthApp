package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.data.remote.NaverApiService
import com.shcl.smarthealth.domain.repository.NaverRepository
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val api : NaverApiService
) : NaverRepository{
    override suspend fun clovaVoice() : String {
        return api.clovaVoice()
    }
}
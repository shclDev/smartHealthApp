package com.shcl.smarthealth.data.repository.dataSoruceImpl

import com.shcl.smarthealth.data.api.NaverApi
import com.shcl.smarthealth.domain.repository.NaverRepository
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val api : NaverApi
) : NaverRepository{
    override suspend fun clovaVoice() : String {
        return api.clovaVoice()
    }
}
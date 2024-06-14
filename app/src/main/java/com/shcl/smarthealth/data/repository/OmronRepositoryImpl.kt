package com.shcl.smarthealth.data.repository

import com.shcl.smarthealth.domain.repository.OmronRepository
import javax.inject.Inject

class OmronRepositoryImpl @Inject constructor(


) : OmronRepository{
    override suspend fun connect() {

    }

    override suspend fun searchDevice() {
    }

    override suspend fun getBloodPressureData() {
    }

    override suspend fun getBodyData() {
    }

    override suspend fun pairing() {

    }


}
package com.shcl.smarthealth.domain.repository

interface OmronRepository {

    suspend fun connect()

    suspend fun searchDevice()

    suspend fun getBloodPressureData()

    suspend fun getBodyData()

    suspend fun pairing()

}
package com.shcl.smarthealth.domain.usecase.isens

import com.shcl.smarthealth.domain.repository.IsensRepository
import com.shcl.smarthealth.presentation.view.device.IsensGlucoseRecordState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class GetGlucoseRecordUseCase @Inject constructor(
    private val repository : IsensRepository
){
    fun requestAllRecords() {
        repository.requestAllRecords()
    }

    fun getDataTransfer() : Flow<IsensGlucoseRecordState> {
        return repository.onDataTransfer(null)
    }
}

package com.shcl.smarthealth.domain.usecase.measurement

import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.model.remote.measurement.HeightRequest
import com.shcl.smarthealth.domain.repository.MeasurmentRepository
import javax.inject.Inject

class UpdateHeightUseCase @Inject constructor(private val repository : MeasurmentRepository){
    suspend operator fun invoke(request : HeightRequest) = repository.updateHeight(request)
}
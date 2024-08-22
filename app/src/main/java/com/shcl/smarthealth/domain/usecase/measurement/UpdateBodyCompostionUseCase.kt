package com.shcl.smarthealth.domain.usecase.measurement

import com.shcl.smarthealth.domain.model.remote.measurement.BloodPressureRequest
import com.shcl.smarthealth.domain.model.remote.measurement.BodyCompositionRequest
import com.shcl.smarthealth.domain.repository.MeasurmentRepository
import javax.inject.Inject

class UpdateBodyCompostionUseCase @Inject constructor(private val repository : MeasurmentRepository){
    suspend operator fun invoke(request : BodyCompositionRequest) = repository.updateBodyCompostion(request)
}

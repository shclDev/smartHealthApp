package com.shcl.smarthealth.domain.usecase.measurement

import com.shcl.smarthealth.domain.model.remote.measurement.BloodGlucoseRequest
import com.shcl.smarthealth.domain.repository.MeasurmentRepository
import javax.inject.Inject

class UpdateBloodGlucoseUseCase @Inject constructor(private val repository : MeasurmentRepository){
    suspend operator fun invoke(request : BloodGlucoseRequest) = repository.updateBloodGlucose(request)
}

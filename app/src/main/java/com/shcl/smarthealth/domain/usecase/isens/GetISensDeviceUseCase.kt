package com.shcl.smarthealth.domain.usecase.isens

import com.shcl.smarthealth.domain.repository.IsensRepository
import javax.inject.Inject

data class GetISensDeviceUseCase @Inject constructor(
    private val repository : IsensRepository
){
    operator fun invoke(category : String) = repository.getDeviceByCategory(category)
}
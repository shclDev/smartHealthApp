package com.shcl.smarthealth.domain.usecase.dashboard

import com.shcl.smarthealth.domain.repository.DashBoardRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository : DashBoardRepository)
{
    suspend operator fun invoke() = repository.getCurrentWeather()
}
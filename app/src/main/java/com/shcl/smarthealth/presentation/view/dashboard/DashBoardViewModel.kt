package com.shcl.smarthealth.presentation.view.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.usecase.dashboard.DashBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardUseCase: DashBoardUseCase
) : ViewModel(){

    private val _nutritionAdvice : MutableStateFlow<String?> = MutableStateFlow ( null)
    val nutritionAdvice : StateFlow<String?> = _nutritionAdvice

    private val _bloodPressure : MutableStateFlow<BloodPressureRoom?> = MutableStateFlow(null)
    val bloodPressure: StateFlow<BloodPressureRoom?> = _bloodPressure

    fun getNutrionAdvice(){
        viewModelScope.launch{
            dashBoardUseCase.getNutritionAdviceUseCase.invoke()
                .onStart { _nutritionAdvice.value = "start"  }
                .onCompletion {  }
                .catch { _nutritionAdvice.value = "error" }
                .collect{
                _nutritionAdvice.value = it
            }
        }
    }

    fun getLastedBloodPressure(){
        viewModelScope.launch {
            dashBoardUseCase.getBloodPressureDBUseCase.invoke()
                .onStart { }
                .onCompletion { }
                .catch { }
                .collect{
                    _bloodPressure.value = it
                }
        }
    }


}
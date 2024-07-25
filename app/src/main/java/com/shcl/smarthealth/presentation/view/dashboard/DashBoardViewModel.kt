package com.shcl.smarthealth.presentation.view.dashboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.model.db.BloodPressureRoom
import com.shcl.smarthealth.domain.model.db.BodyCompositionRoom
import com.shcl.smarthealth.domain.model.db.GlucoseRecordRoom
import com.shcl.smarthealth.domain.model.remote.weather.WeatherResponse
import com.shcl.smarthealth.domain.usecase.dashboard.DashBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardUseCase: DashBoardUseCase
) : ViewModel(){

    private val _nutritionAdvice : MutableStateFlow<String?> = MutableStateFlow ( null)
    val nutritionAdvice : StateFlow<String?> = _nutritionAdvice

    private val _bloodPressure : MutableStateFlow<BloodPressureRoom?> = MutableStateFlow(null)
    val bloodPressure: StateFlow<BloodPressureRoom?> = _bloodPressure

    private val _bodyComposition : MutableStateFlow<BodyCompositionRoom?> = MutableStateFlow(null)
    val bodyComposition: StateFlow<BodyCompositionRoom?> = _bodyComposition

    private val _glucose : MutableStateFlow<GlucoseRecordRoom?> = MutableStateFlow(null)
    val glucose : StateFlow<GlucoseRecordRoom?> = _glucose

    private val _weatherResponse = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponse : StateFlow<WeatherResponse?> = _weatherResponse


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

    fun getLastedGlucose(){
        viewModelScope.launch {
            dashBoardUseCase.getGlucoseDBUseCase.invoke()
                .onStart { Log.d("smartHealth" , "glucose!!")}
                .onCompletion { }
                .catch { }
                .collect{
                    _glucose.value = it
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

    fun getLastedWeight(){
        viewModelScope.launch {
            dashBoardUseCase.getWeightDBUseCase.invoke()
                .onStart { }
                .onCompletion { }
                .catch { }
                .collect{
                    _bodyComposition.value = it
                }
        }

    }

    fun getCurrentWeather(){
        viewModelScope.launch {
            launch {
                dashBoardUseCase.getWeatherUseCase.invoke()
                    .onStart {  }
                    .onCompletion {  }
                    .catch {  }
                    .collect{
                        // Log.d("weather" , ${_})
                        it?.let {
                            _weatherResponse.value = it
                            Log.d("weather" , "${it.main.temp}" )
                        }
                    }
            }
        }
    }


}
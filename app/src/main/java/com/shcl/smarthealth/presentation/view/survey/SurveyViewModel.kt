package com.shcl.smarthealth.presentation.view.survey

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shcl.smarthealth.presentation.view.device.ScanDeviceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
) : ViewModel() {

    private val _levelState = MutableStateFlow(1)
    val levelState = _levelState.asStateFlow()

    private val _level1Validation = MutableStateFlow(false)
    val level1Validation = _level1Validation.asStateFlow()

    private val _level2Validation = MutableStateFlow(false)
    val level2Validation = _level2Validation.asStateFlow()

    private val _level3Validation = MutableStateFlow(false)
    val level3Validation = _level3Validation.asStateFlow()

    private val _level4Validation = MutableStateFlow(false)
    val level4Validation = _level4Validation.asStateFlow()

    private val _surveyComplete = MutableStateFlow(false)
    val surveyComplete = _surveyComplete.asStateFlow()

    init {


    }

    fun next(){
        _levelState.value = _levelState.value + 1;
    }

    fun prev(){
        if(_levelState.value >= 2)
            _levelState.value = _levelState.value - 1
    }

    fun surveyComplete(){


    }
}
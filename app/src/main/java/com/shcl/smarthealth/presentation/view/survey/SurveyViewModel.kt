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


enum class SurveyByLevel(val title : String, val desc:String){

    LEVEL1("식습관" , "식습관"),
    LEVEL2("수면습관","수면습관"),
    LEVEL3("우울감","우울감"),
    LEVEL4("흡연/음주","흡연 및 음주"),
    LEVEL5("신체활동 / 질병력 및 가족력" , "평소 신체활동")

}


@HiltViewModel
class SurveyViewModel @Inject constructor(
) : ViewModel() {

    private val MAX_LEVEL = 5
    private val MIN_LEVEL = 1

    private val _levelState = MutableStateFlow(1)
    val levelState = _levelState.asStateFlow()

    private val _levelTitleState = MutableStateFlow(SurveyByLevel.LEVEL1)
    val levelTitleState = _levelTitleState.asStateFlow()

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
        if(_levelState.value + 1 >= MAX_LEVEL){
            _levelState.value = MAX_LEVEL
        }else{
            _levelState.value = _levelState.value + 1;
        }
        levelTitle(_levelState.value)
    }

    fun levelTitle(level : Int){
        when(level){
            1-> _levelTitleState.value = SurveyByLevel.LEVEL1
            2-> _levelTitleState.value = SurveyByLevel.LEVEL2
            3-> _levelTitleState.value = SurveyByLevel.LEVEL3
            4-> _levelTitleState.value = SurveyByLevel.LEVEL4
        }
    }

    fun prev(){
        if(_levelState.value - 1 <= MIN_LEVEL){
            _levelState.value = MIN_LEVEL
        }else{
            _levelState.value = _levelState.value - 1
        }
        levelTitle(_levelState.value)
    }

    fun surveyComplete(){


    }
}
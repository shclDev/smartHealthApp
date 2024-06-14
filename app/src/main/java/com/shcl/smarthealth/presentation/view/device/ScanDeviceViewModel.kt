package com.shcl.smarthealth.presentation.view.device

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.common.Resource
import com.shcl.smarthealth.domain.usecase.ble.ScanDeviceUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ScanDeviceViewModel @Inject constructor(
    private val scanDeviceUseCase: ScanDeviceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(ScanDeviceState())
    val state : State<ScanDeviceState> = _state


    private fun scanDevice(){

        scanDeviceUseCase().onEach{ result->
            when(result){
                is Resource.Success -> {
                    _state.value = ScanDeviceState()
                }
                is Resource.Error -> {

                }
                is Resource.Loading->{

                }

            }

        }.launchIn(viewModelScope)


    }


}
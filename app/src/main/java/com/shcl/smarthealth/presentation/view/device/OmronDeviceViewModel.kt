package com.shcl.smarthealth.presentation.view.device


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.domain.usecase.ble.OmronDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OmronDeviceViewModel @Inject constructor(
    private val omronDeviceUseCase: OmronDeviceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = MutableStateFlow(ScanDeviceState())
    val state : StateFlow<ScanDeviceState> = _state


    fun scanDevice(){

        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.invoke().collect(){
                    _state.value = ScanDeviceState( scannedDevices = it )
                }
            }
    }

    fun stopScan(){
        omronDeviceUseCase.scanDeviceUseCase.stopDevice()
    }



        /*
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
        */

}
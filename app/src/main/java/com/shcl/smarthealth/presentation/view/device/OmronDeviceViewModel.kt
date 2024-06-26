package com.shcl.smarthealth.presentation.view.device


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shcl.smarthealth.common.GlobalVariables
import com.shcl.smarthealth.domain.model.omron.DiscoveredDevice
import com.shcl.smarthealth.domain.usecase.ble.OmronDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OmronDeviceViewModel @Inject constructor(
    private val omronDeviceUseCase: OmronDeviceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = MutableStateFlow(ScanDeviceState())
    val state = _state.asStateFlow()

    private val _testState = MutableStateFlow(0)
    val testState = _testState.asStateFlow()


    init {


        GlobalVariables.coroutineScope = viewModelScope
        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.testStateFlow(viewModelScope).collect{
                Log.d("test" , "${it}")
                _testState.value = it
            }
        }

        /*
        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.onScan().collect{
                Log.d("sdevice" , "size : ${it?.size}")
                _deviceState.value = it
            }
        }*/



        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.onScan()
                .onStart { Log.d("sdevice","scan on start") }
                .onCompletion { Log.d("sdevice","scan on completion") }
                .catch { Log.d("sdevice" , "error!!") }
                .collect{finedDevices->
                    finedDevices.let{
                        if(it.isEmpty()){
                            Log.d("sdevice","scan device is empty")
                        }else{
                            Log.d("sdevice","scan device size ${it.size}")
                            _state.value = ScanDeviceState(scannedDevices = it.toMutableList())
                        }
                    }
                }
        }

    }
    fun scanDevice(){
        omronDeviceUseCase.scanDeviceUseCase.searchDevices()

        /*
        viewModelScope.launch {
            omronDeviceUseCase.scanDeviceUseCase.onScan().collect{


                    Log.d("omron", "viewmodel :device - ${it}")
                    var temp : List<DiscoveredDevice> = mutableListOf()
                    temp.plus(DiscoveredDevice(address = "@DFASDF#@$@$"))
                    _state.value = ScanDeviceState( scannedDevices = temp )
                }
        }*/



    }

    fun stopScan(){
        Log.d("omron", "viewModel - stopDevice")
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
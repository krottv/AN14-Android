package com.github.krottv.tmstemp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow<String?>(null)
    val state: StateFlow<String?> = _stateFlow

    var job: Job? = null

    fun getData() {
        job = viewModelScope.launch {
            for (i in 1..3) {
                _stateFlow.value = "$i"
                delay(1000L)
            }
            _stateFlow.value = "Successful"
        }
    }
}
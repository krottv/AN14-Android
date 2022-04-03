package com.github.krottv.tmstemp

import android.os.CountDownTimer
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow: MutableStateFlow<String?> = MutableStateFlow<String?>(null)
    val state: StateFlow<String?> = _stateFlow

    var job: Job? = null

    fun loadDataOnStart(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_START) {
                    loadData()
                }
            }
        })
    }

    fun loadData() {
        if (job != null) return
        job = viewModelScope.launch {
            for (i in 1..8) {
                _stateFlow.value = "Downloading data... $i"
                delay(1000L)
            }
            _stateFlow.value = "Downloading completed"
        }

    }
}
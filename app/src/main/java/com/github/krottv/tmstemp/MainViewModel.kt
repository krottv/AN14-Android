package com.github.krottv.tmstemp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private companion object {
        const val MAX_SIZE_TEXT: Int = 40
        const val MIN_SIZE_TEXT: Int = 10
    }

    private val stateFlow = MutableStateFlow(State(textSizeFirst = 12F, textSizeSecond = 16F))
    val state: StateFlow<State> = stateFlow
    fun onProgressChanged(seekNumber: Int, seekProgress: Int, seekMax: Int) {
        val newTextSize =
            ((MAX_SIZE_TEXT - MIN_SIZE_TEXT) * seekProgress / seekMax + MIN_SIZE_TEXT).toFloat()
        stateFlow.value = when (seekNumber) {
            0 -> state.value.copy(textSizeFirst = newTextSize)
            1 -> state.value.copy(textSizeSecond = newTextSize)
            else -> throw IllegalStateException("invalid seekNumber")
        }
    }
}
package com.github.krottv.tmstemp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalStateException

private const val MAX_TEXT_SIZE: Int = 60
private const val MIN_TEXT_SIZE: Int = 9

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState(textSize1 = 10F, textSize2 = 10F))
    val state: StateFlow<MainState> = _state

    fun onProgressChanged(seekNumber: Int, seekProgress: Int, seekMax: Int) {
        val newTextSize =
            ((MAX_TEXT_SIZE - MIN_TEXT_SIZE) * seekProgress / seekMax + MIN_TEXT_SIZE).toFloat()
        _state.value = when (seekNumber) {
            0 -> state.value.copy(textSize1 = newTextSize)
            1 -> state.value.copy(textSize2 = newTextSize)
            else -> throw IllegalStateException("invalid seekNumber")
        }
    }
}
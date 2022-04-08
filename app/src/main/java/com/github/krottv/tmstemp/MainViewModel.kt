package com.github.krottv.tmstemp

import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.IllegalStateException

class MainViewModel: ViewModel() {

    private companion object {
        const val MIN_SIZE_OF_FIRST_TEXT = 4
        const val MAX_SIZE_OF_FIRST_TEXT = 14
        const val MIN_SIZE_OF_SECOND_TEXT = 10
        const val MAX_SIZE_OF_SECOND_TEXT = 14
        const val START_TEXT_SIZE_OF_FIRST_TEXT = 9f
        const val START_TEXT_SIZE_OF_SECOND_TEXT = 12f
    }

    private val _state = MutableStateFlow(SeekBarTrackerForTextSize(START_TEXT_SIZE_OF_FIRST_TEXT, START_TEXT_SIZE_OF_SECOND_TEXT))
    val state: StateFlow<SeekBarTrackerForTextSize> = _state.asStateFlow()

    fun onProgressChanged(seekBar: SeekBar, progress: Int, seekBarNumber: Int) {
        _state.value = when (seekBarNumber) {
            0 -> state.value.copy(textSize1 = ((MAX_SIZE_OF_FIRST_TEXT - MIN_SIZE_OF_FIRST_TEXT) * (progress / seekBar.max.toFloat())) + MIN_SIZE_OF_FIRST_TEXT)
            1 -> state.value.copy(textSize2 = ((MAX_SIZE_OF_SECOND_TEXT - MIN_SIZE_OF_SECOND_TEXT) * (progress / seekBar.max.toFloat())) + MIN_SIZE_OF_SECOND_TEXT)
            else -> throw IllegalStateException("Invalid seekBarNumber")
        }
    }

}
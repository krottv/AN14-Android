package com.github.krottv.tmstemp

import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.github.krottv.tmstemp.databinding.HomeworkBinding

class MainViewModel : ViewModel() {

    private companion object {
        const val MAX_SIZE_TEXT: Int = 40
        const val MIN_SIZE_TEXT: Int = 15
    }

    fun setLayoutTextSize(layout: HomeworkBinding, textId: Int) {
        val textView: TextView
        val seekBar: SeekBar
        when (textId) {
            layout.firstText.id -> {
                textView = layout.firstText
                seekBar = layout.seekBarFirstChange
            }
            layout.secondText.id -> {
                textView = layout.secondText
                seekBar = layout.seekBarSecondChange
            }
            else -> throw IllegalStateException("Invalid textId $textId")
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textView.textSize =
                    ((MAX_SIZE_TEXT - MIN_SIZE_TEXT) * p1 / seekBar.max + MIN_SIZE_TEXT).toFloat()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}
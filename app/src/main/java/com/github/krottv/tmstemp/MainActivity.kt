package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private companion object {
        const val PROGRESS_OF_SEEKBAR_AT_START = 50
    }

    private lateinit var layout: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private fun setTextSizeFromSeekBar(seekBar: SeekBar, seekBarNumber: Int) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewModel.onProgressChanged(seekBar, progress, seekBarNumber)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(layout.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        layout.internetImage.load("https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80") {
            transformations(CircleCropTransformation())
        }

        layout.trim.load(R.drawable.ic_trim)
        layout.move.load(R.drawable.move_icon)
        layout.color.load(R.drawable.ic_color)
        layout.volume.load(R.drawable.ic_volume)

        layout.seekBar.progress = PROGRESS_OF_SEEKBAR_AT_START
        layout.seekBar2.progress = PROGRESS_OF_SEEKBAR_AT_START

        setTextSizeFromSeekBar(layout.seekBar, 0)
        setTextSizeFromSeekBar(layout.seekBar2, 1)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                layout.dynamicText1.textSize = it.textSize1
                layout.dynamicText2.textSize = it.textSize2
            }
        }
    }
}
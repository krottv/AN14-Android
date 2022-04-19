package com.github.krottv.tmstemp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.databinding.HomeworkBinding

class MainActivity : AppCompatActivity() {

    lateinit var layout: HomeworkBinding
    private var mainViewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = HomeworkBinding.inflate(LayoutInflater.from(this))
        layout.imageViewInternetPicture.load(
            "https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2." +
                    "1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80 "
        ) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        setContentView(layout.root)

        fun setSeekBar(seekBar: SeekBar, seekBarNumber: Int) {
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    mainViewModel.onProgressChanged(seekBarNumber, p1, seekBar.max)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })
        }

        setSeekBar(layout.seekBarFirstChange, 0)
        setSeekBar(layout.seekBarSecondChange, 1)

        lifecycleScope.launchWhenStarted {
            mainViewModel.state.collect {
                layout.firstText.textSize = it.textSizeFirst
                layout.secondText.textSize = it.textSizeSecond
            }
        }
    }
}



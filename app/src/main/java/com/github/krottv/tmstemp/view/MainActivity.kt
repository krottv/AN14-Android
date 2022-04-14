package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMain: ActivityMainBinding
    val viewModel: MainViewModel = MainViewModel()

    private fun setSeekBar(seekBar: SeekBar, seekNumber: Int) {

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.onProgressChanged(seekNumber, p1, seekBar.max)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMain.root)

        setSeekBar(activityMain.seekBar, 0)
        setSeekBar(activityMain.seekBar2, 1)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                activityMain.textView.textSize = it.textSize1
                activityMain.textView2.textSize = it.textSize2
            }
        }

        activityMain.imageView7.load("https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80") {
            transformations(CircleCropTransformation())
        }
    }
}
package com.github.krottv.tmstemp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMain: ActivityMainBinding
    val maxSizeText: Int = 70
    val minSizeText: Int = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMain.root)

        activityMain.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                activityMain.textView.textSize =
                    ((maxSizeText - minSizeText) * p1 / activityMain.seekBar.max + minSizeText).toFloat()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        activityMain.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                activityMain.textView2.textSize =
                    ((maxSizeText - minSizeText) * p1 / activityMain.seekBar2.max + minSizeText).toFloat()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        activityMain.imageView7.load("https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80") {
            transformations(CircleCropTransformation())
        }
    }
}
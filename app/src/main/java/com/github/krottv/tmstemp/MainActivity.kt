package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var layuot: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layuot = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(layuot.root)
        layuot.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val minSize = 9
                val maxSize = 50
                val newSize = ((maxSize - minSize) * (progress / seekBar.max.toFloat())) + minSize
                layuot.textView.textSize = newSize

            }


            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        layuot.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val minSize = 9
                val maxSize = 50
                val newSize = ((maxSize - minSize) * (progress / seekBar.max.toFloat())) + minSize
                layuot.textView2.textSize = newSize

            }


            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        layuot.imageView7.load("https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80") {
            transformations(CircleCropTransformation())
            layuot.seekBar.progress = 0
            layuot.seekBar2.progress = 0

        }
    }
}
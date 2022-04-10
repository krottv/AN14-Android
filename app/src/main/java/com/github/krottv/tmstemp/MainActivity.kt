package com.github.krottv.tmstemp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
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

        mainViewModel.setLayoutTextSize(layout, layout.firstText.id)
        mainViewModel.setLayoutTextSize(layout, layout.secondText.id)
    }
}



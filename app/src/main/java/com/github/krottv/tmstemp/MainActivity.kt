package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var roundImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roundImage = findViewById(R.id.img)
        roundImage.load("https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80"){
            crossfade(true)
            placeholder(R.drawable.ic_move_icon)
            transformations(CircleCropTransformation())
        }
    }
}
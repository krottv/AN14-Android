package com.github.krottv.tmstemp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.transition.Scene
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMain = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMain.root)

        activityMain.imageView.load("https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
        activityMain.imageView2.load("https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
        activityMain.imageView3.load("https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
        ObjectAnimator().apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f),
            )
            target = activityMain.layer
            start()
        }
        val scene = Scene.getSceneForLayout(activityMain.root, R.layout.second_paragraph, this)
        activityMain.textView4.setOnClickListener {
           scene.enter()
        }

        val valueAnimator = ValueAnimator().apply {
            duration = 10000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setFloatValues(1f, 0f)
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            activityMain.imageView3.alpha = value
            activityMain.textView5.alpha = value
            activityMain.textView6.alpha = value
        }
        valueAnimator.start()

    }
}
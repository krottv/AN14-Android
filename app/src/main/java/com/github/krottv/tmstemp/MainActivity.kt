package com.github.krottv.tmstemp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Group
import androidx.transition.ChangeBounds
import androidx.transition.Scene
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityLayoutTransition = MainActivityLayoutTransition(this)

        mainActivityLayoutTransition.bind()
    }
}
package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.krottv.tmstemp.binder.MainActivityDataBinder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityDataBinder: MainActivityDataBinder = MainActivitySceneDataBinder(this)
        mainActivityDataBinder.bind()
    }
}
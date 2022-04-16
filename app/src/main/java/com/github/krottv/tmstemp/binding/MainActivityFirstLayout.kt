package com.github.krottv.tmstemp.binding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.github.krottv.tmstemp.databinding.ActivityFirstBinding
import com.github.krottv.tmstemp.databinding.ActivitySecondBinding

class MainActivityFirstLayout : AppCompatActivity() {

    private lateinit var layoutFirst: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        layoutFirst = ActivityFirstBinding.inflate(LayoutInflater.from(this))
        layoutFirst.imageViewFirst.load(
            "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-" +
                    "1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )

        layoutFirst.imageViewSecond.load(
            "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib" +
                    "=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )
        layoutFirst.imageViewThird.load(
            "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-" +
                    "1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )

        val mainActivityDataBinder: MainActivityDataBinder = MainActivityLayoutTransition(this, layoutFirst)
        mainActivityDataBinder.bind()

        setContentView(layoutFirst.root)

    }

}
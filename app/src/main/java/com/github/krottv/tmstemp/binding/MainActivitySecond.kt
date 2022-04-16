package com.github.krottv.tmstemp.binding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.github.krottv.tmstemp.databinding.ActivitySecondBinding

class MainActivitySecond : AppCompatActivity() {

    private lateinit var layoutSecond: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutSecond = ActivitySecondBinding.inflate(LayoutInflater.from(this))
        layoutSecond.imageViewSecondBig.load(
            "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib" +
                    "=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )
        setContentView(layoutSecond.root)
    }
}
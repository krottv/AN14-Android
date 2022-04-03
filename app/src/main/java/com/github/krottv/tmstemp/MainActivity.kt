package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        findViewById<Button>(R.id.buttonSendRequest).setOnClickListener() {
            viewModel.getData()

        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                textView.text = it
            }
        }
    }
}
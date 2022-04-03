package com.github.krottv.tmstemp


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        textView = findViewById(R.id.text)

        findViewById<Button>(R.id.download).setOnClickListener {
            viewModel.loadData()
        }
        lifecycleScope.launch {
            viewModel.state.collect {
                textView.text = it
            }
        }
    }
}
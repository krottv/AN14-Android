package com.github.krottv.tmstemp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.krottv.tmstemp.presentation.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var mainActivityDataBinder: MainActivityDataBinder

        super.onCreate(savedInstanceState)

        mainActivityDataBinder = MainActivityBinder(this)
        mainActivityDataBinder.bind()

        val viewModel: PostViewModel = ViewModelProvider(this)[PostViewModel::class.java]

        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.state.collect {
                if (it != null) {
                    if (it.isSuccess) {
                        mainActivityDataBinder.dataLoaded(it.getOrThrow())
                    } else {
                        mainActivityDataBinder.showError(it.exceptionOrNull() as Throwable)
                    }
                } else {
                    mainActivityDataBinder.showProgressBar()
                }
            }
        }
    }
}
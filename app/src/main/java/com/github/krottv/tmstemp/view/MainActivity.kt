package com.github.krottv.tmstemp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.krottv.tmstemp.presentation.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        val mainActivityBinder = MainActivityBinder(this)
        mainActivityBinder.bindView()

        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.state.collect {
                if (it != null) {
                    if (it.isSuccess) {
                        mainActivityBinder.PostsScene()
                        mainActivityBinder.onDataLoaded(it.getOrThrow())
                    }
                    if (it.isFailure) {
                        mainActivityBinder.ErrorScene(it.exceptionOrNull() as Throwable)
                    }
                }
            }
        }
    }
}
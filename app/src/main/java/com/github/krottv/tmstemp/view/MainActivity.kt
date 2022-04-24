package com.github.krottv.tmstemp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.krottv.tmstemp.binder.MainActivityDataBinder
import com.github.krottv.tmstemp.binder.MainActivityRecyclerScrollDataBinder
import com.github.krottv.tmstemp.presentation.MessageViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var mainActivityDataBinder: MainActivityDataBinder

        super.onCreate(savedInstanceState)

        mainActivityDataBinder = MainActivityRecyclerScrollDataBinder(this)
        mainActivityDataBinder.bind()

        val viewModel: MessageViewModel = ViewModelProvider(this)[MessageViewModel::class.java]

        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.state.collect {
                if (it != null) {
                    if (it.isSuccess) {
                        mainActivityDataBinder.onDataLoaded(it.getOrThrow())
                    } else {
                        mainActivityDataBinder.showError(it.exceptionOrNull() as Throwable)
                    }
                } else {
                    mainActivityDataBinder.showProgress()
                }
            }
        }
    }
}
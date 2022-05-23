package com.github.krottv.tmstemp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.binder.MainActivityDataBinder
import com.github.krottv.tmstemp.binder.MainActivityRecyclerScrollDataBinder
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityDataBinder: MainActivityDataBinder = MainActivityRecyclerScrollDataBinder(this)
        mainActivityDataBinder.bind()
    }
}
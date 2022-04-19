package com.github.krottv.tmstemp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.krottv.tmstemp.domain.PostModel
import com.github.krottv.tmstemp.presentation.PostsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binder: MainActivityBinder
    lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]

        binder = MainActivityBinder(this)
        binder.bindView()

        viewModel.loadData()
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                if (it != null) {

                    if (it.isSuccess) {
                        binder.applyAdapter(it.getOrThrow() as MutableList<PostModel>)
                    }
                } else {
                    binder.showProgress()
                }
            }
        }

    }
}
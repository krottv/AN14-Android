package com.github.krottv.tmstemp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.transition.Scene
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.presentation.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var scene = Scene(findViewById(R.id.scene_root))
        var loadScene = Scene.getSceneForLayout(scene, R.layout.load_scene, this)
        loadScene.enter()
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        val mainActivityBinder = MainActivityBinder(this)
        mainActivityBinder.bindView()


        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.state.collect {
                if (it != null) {
                    if (it.isSuccess) {
                        mainActivityBinder.onDataLoaded(it.getOrThrow())
                    }
                }
            }
        }
    }
}
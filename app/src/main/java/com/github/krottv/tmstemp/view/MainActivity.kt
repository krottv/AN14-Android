package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.transition.Scene
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.presentation.PostViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        var scene = ActivityMainBinding.inflate(LayoutInflater.from(this))
        var loadScene = Scene.getSceneForLayout(scene.sceneRoot, R.layout.load_scene, this)
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
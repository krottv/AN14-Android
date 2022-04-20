package com.github.krottv.tmstemp.view

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.databinding.PostsSceneBinding
import com.github.krottv.tmstemp.domain.PostModel

class MainActivityBinder(val activity: Activity) {

    private lateinit var binding: RecyclerView
    private lateinit var rootScene: ActivityMainBinding
    private lateinit var loadScene: Scene
    private lateinit var postsScene: Scene

    fun bindView() {

        rootScene = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(rootScene.root)

        launchLoadScene()

        activity.findViewById<TextView>(R.id.buttonDelete)
            .setOnClickListener {
                (binding.adapter as PostsAdapter).removeFirstItem()
            }
    }

    private fun launchLoadScene() {
        loadScene = Scene.getSceneForLayout(rootScene.sceneRoot, R.layout.load_scene, activity)
        TransitionManager.go(loadScene)
    }

    fun launchPostsScene() {
        postsScene = Scene.getSceneForLayout(rootScene.sceneRoot, R.layout.posts_scene, activity)
        TransitionManager.go(postsScene)
        binding = rootScene.sceneRoot.findViewById(R.id.recyclerView)

        val layoutManger = LinearLayoutManager(activity, VERTICAL, false)

        binding.layoutManager = layoutManger
    }

    fun launchErrorScene(e: Throwable) {
        val errorScene =
            Scene.getSceneForLayout(rootScene.sceneRoot, R.layout.error_scene, activity)

        TransitionManager.go(errorScene)

        val error = rootScene.sceneRoot.findViewById<TextView>(R.id.error)

        error.text = e.message
    }

    fun onDataLoaded(list: List<PostModel>) {
        if (binding.adapter == null) {
            binding.adapter = PostsAdapter(list)
        } else {
            (binding.adapter as PostsAdapter).data = list as MutableList<PostModel>
        }
    }
}
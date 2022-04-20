package com.github.krottv.tmstemp.view

import android.app.Activity
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Scene
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel

class MainActivityBinder(val activity: Activity) {

    private lateinit var binding: RecyclerView


    fun bindView() {
        var sceneRoot = Scene(activity.findViewById<ViewGroup>(R.id.scene_root))

        binding = activity.findViewById(R.id.recyclerView)
        activity.setContentView(R.layout.activity_main)

        val postsScene = Scene.getSceneForLayout(sceneRoot, R.layout.posts_scene, activity)
        postsScene.enter()

        val layoutManger = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        binding.layoutManager = layoutManger

        activity.findViewById<TextView>(R.id.buttonDelete)
            .setOnClickListener {
                (binding.adapter as PostsAdapter).removeFirstItem()
            }
    }

    fun onDataLoaded(list: List<PostModel>) {
        if (binding.adapter == null) {

            Log.i("myi", "load1")

            binding.adapter = PostsAdapter(list)
        } else {
            Log.i("myi", "load2")
            (binding.adapter as PostsAdapter).data = list as MutableList<PostModel>
        }

    }


}
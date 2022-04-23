package com.github.krottv.tmstemp.view

import android.app.Activity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.domain.PostModel


class MainActivityBinder(private val activity: Activity) : MainActivityDataBinder {
    private lateinit var container: ActivityMainBinding

    override fun bind() {
        container = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(container.root)
    }

    fun dataLoaded(data: List<PostModel>) {

        val scene = Scene.getSceneForLayout(container.container, R.layout.recycler_view, activity)
        scene.setEnterAction {
            container.container.findViewById<TextView>(R.id.textViewDelete).setOnClickListener {
                removeOne()
            }
        }
        TransitionManager.go(scene)

        val recyclerView = container.container.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        if (recyclerView.adapter == null) {
            recyclerView.adapter = PostsAdapter(data)
        } else {
            (recyclerView.adapter as PostsAdapter).data = data as MutableList<PostModel>
        }
    }

    fun showError(exception: Throwable) {

        val scene = Scene.getSceneForLayout(container.container, R.layout.error, activity)

        TransitionManager.go(scene)
        container.container.findViewById<TextView>(R.id.textViewError).apply {
            text = exception.message
        }
    }

    fun showProgressBar() {
        val scene = Scene.getSceneForLayout(container.container, R.layout.progrees_bar, activity)
        TransitionManager.go(scene)
    }

    private fun removeOne() {
        val recycler = container.container.findViewById<RecyclerView>(R.id.recyclerView)
        (recycler.adapter as PostsAdapter).removeOne()
        recycler.itemAnimator = object : DefaultItemAnimator() {}
    }
}



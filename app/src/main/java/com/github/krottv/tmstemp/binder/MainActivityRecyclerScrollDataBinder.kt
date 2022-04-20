package com.github.krottv.tmstemp.binder

import android.app.Activity
import android.transition.*
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.domain.Message
import com.github.krottv.tmstemp.view.MessageAdapter

class MainActivityRecyclerScrollDataBinder(private val activity: Activity): MainActivityDataBinder {

    private lateinit var container: ActivityMainBinding

    private val customTransition = TransitionSet().apply {
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
    }

    override fun bind() {
        container = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(container.root)
    }

    fun onDataLoaded(data: List<Message>) {

        val scene = Scene.getSceneForLayout(container.container, R.layout.recycler_view, activity)

        scene.setEnterAction {
            container.container.findViewById<TextView>(R.id.textView).setOnClickListener {
                removeOne()
            }
        }

        TransitionManager.go(scene, customTransition)

        val recyclerView = container.container.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        if (recyclerView.adapter == null) {
            recyclerView.adapter = MessageAdapter(data)
        } else {
            (recyclerView.adapter as MessageAdapter).data = data
        }
    }

    fun showError() {

        val scene = Scene.getSceneForLayout(container.container, R.layout.show_error, activity)

        TransitionManager.go(scene, customTransition)

        container.container.findViewById<TextView>(R.id.errorText).apply {
            text = "Server Error"
        }
    }

    fun showProgress() {
        val scene = Scene.getSceneForLayout(container.container, R.layout.loading, activity)
        TransitionManager.go(scene, customTransition)
    }

    private fun removeOne() {
        val element = (container.container.findViewById<RecyclerView>(R.id.recycler).adapter as MessageAdapter)
        element.data = element.data.subList(1, element.itemCount)
        element.notifyItemRemoved(0)
    }
}
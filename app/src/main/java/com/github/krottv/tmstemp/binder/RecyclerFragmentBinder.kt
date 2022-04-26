package com.github.krottv.tmstemp.binder

import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.SceneContainerBinding
import com.github.krottv.tmstemp.domain.MessageModel
import com.github.krottv.tmstemp.view.MessageAdapter

class RecyclerFragmentBinder(private val fragment: Fragment, private val onItemClick: (View, MessageModel) -> Unit) {

    private lateinit var binding: SceneContainerBinding

    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SceneContainerBinding.inflate(inflater, container, false)

        return binding.root
    }


    private val customTransition = TransitionSet().apply {
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
    }

    fun onDataLoaded(data: List<MessageModel>) {

        val scene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.messages_fragment, fragment.requireContext())
        TransitionManager.go(scene, customTransition)

        val recyclerView = scene.sceneRoot.findViewById<RecyclerView>(R.id.container)
        recyclerView.layoutManager = LinearLayoutManager(fragment.requireContext())

        if (recyclerView.adapter == null) {
            recyclerView.adapter = MessageAdapter(data, onItemClick)
        } else {
            (recyclerView.adapter as MessageAdapter).data = data
        }
    }

    fun showError(exception: Throwable) {

        val scene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.show_error, fragment.requireContext())
        TransitionManager.go(scene, customTransition)

        scene.sceneRoot.findViewById<TextView>(R.id.errorText).apply {
            text = exception.message
        }
    }

    fun showProgress() {

        val scene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.loading, fragment.requireContext())
        TransitionManager.go(scene, customTransition)
    }
}
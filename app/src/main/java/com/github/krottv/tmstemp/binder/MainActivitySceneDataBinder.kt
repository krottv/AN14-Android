package com.github.krottv.tmstemp.binder

import android.app.Activity
import android.view.LayoutInflater
import com.github.krottv.tmstemp.animation.MainTransitionController
import com.github.krottv.tmstemp.databinding.StartActivityBinding

class MainActivitySceneDataBinder(private val activity: Activity): MainActivityDataBinder {
    override fun bind() {

        val startLayout = StartActivityBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(startLayout.root)

        val transitionController = MainTransitionController(activity, startLayout)
        transitionController.performTransition()
    }
}
package com.github.krottv.tmstemp.binder

import android.app.Activity
import android.view.LayoutInflater
import com.github.krottv.tmstemp.animation.MainTransitionController
import com.github.krottv.tmstemp.databinding.ActivityStartBinding

class MainActivitySceneDataBinder(private val activity: Activity): MainActivityDataBinder {
    override fun bind() {

        val startLayout = ActivityStartBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(startLayout.root)

        val transitionController = MainTransitionController(activity, startLayout)
        transitionController.performTransition()
    }
}
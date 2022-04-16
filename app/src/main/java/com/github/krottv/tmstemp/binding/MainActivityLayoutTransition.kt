package com.github.krottv.tmstemp.binding

import android.app.Activity
import android.view.LayoutInflater
import androidx.core.view.doOnPreDraw
import com.github.krottv.tmstemp.animation.ElementsAnimationHelper
import com.github.krottv.tmstemp.animation.MainTransitionController
import com.github.krottv.tmstemp.databinding.ActivityFirstBinding
import com.github.krottv.tmstemp.databinding.ActivitySecondBinding


class MainActivityLayoutTransition(val activity: Activity, val layout:ActivityFirstBinding): MainActivityDataBinder {

    override fun bind() {
       // val binding1: ActivityFirstBinding = ActivityFirstBinding.inflate(LayoutInflater.from(activity))
        //activity.setContentView(binding1.root)

        val transitionController = MainTransitionController(activity,layout)
        val elementsAnimationHelper = ElementsAnimationHelper()

       layout.imageViewSecond.setOnClickListener {
            transitionController.performTransition()
        }

        layout.root.doOnPreDraw {
            elementsAnimationHelper.animateScaleEndlessly(layout.imageViewSecond)
        }
    }
}
package com.github.krottv.tmstemp.animation

import android.app.Activity
import androidx.transition.*
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityFirstBinding


class MainTransitionController(val activity: Activity, val binding: ActivityFirstBinding) {

    private var transitionStep: TransitionStep = TransitionStep.ACTIVITY_FIRST

    fun performTransition() {
        when (transitionStep) {
            TransitionStep.ACTIVITY_FIRST -> {
                transitionToSecondFromFirst()
            }
            TransitionStep.ACTIVITY_SECOND -> {
                performTransitionToFirstFromSecond()
            }
        }

    }

    private fun transitionToSecondFromFirst() {
        transitionStep = TransitionStep.ACTIVITY_FIRST

        val scene1 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.activity_second, activity)

        val customTransition = TransitionSet().apply {
            addTransition(Fade().addTarget(R.id.imageViewSecondBig))
            addTransition(
                Slide().addTarget(R.id.textViewSecondPoint)
                    .addTarget(R.id.textViewSecond)
            )
        }

        TransitionManager.go(scene1, customTransition)
    }

    private fun performTransitionToFirstFromSecond() {
        transitionStep = TransitionStep.ACTIVITY_SECOND

        val scene2 = Scene.getSceneForLayout(binding.sceneRoot, R.layout.activity_first, activity)

        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())

        }
        transition.ordering = TransitionSet.ORDERING_TOGETHER
        TransitionManager.go(scene2, transition)
    }


    enum class TransitionStep {
        ACTIVITY_FIRST, ACTIVITY_SECOND
    }
}
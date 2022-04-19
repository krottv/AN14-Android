package com.github.krottv.tmstemp.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Layer
import androidx.transition.*
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.databinding.ActivityStartBinding


class MainTransitionController(
    private val activity: Activity,
    private val binding: ActivityStartBinding
) {

    private var transitionStep: TransitionStep = TransitionStep.EMPTY

    enum class TransitionStep {
        EMPTY, FIRST, SECOND
    }

    private val customTransition = TransitionSet().apply {
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
    }

    fun performTransition(): Unit = when (transitionStep) {
        TransitionStep.EMPTY -> {
            transitionToFirst()
        }
        TransitionStep.FIRST -> {
            transitionToSecond()
        }
        TransitionStep.SECOND -> {
            transitionToFirst()
        }
    }

    @SuppressLint("CutPasteId")
    private fun transitionToFirst() {
        transitionStep = TransitionStep.FIRST

        val scene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.activity_first, activity)

        scene.setEnterAction {

            scene.sceneRoot.findViewById<ImageView>(R.id.imageViewFirst)
                .load("https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
            scene.sceneRoot.findViewById<ImageView>(R.id.imageViewSecond)
                .load("https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
            scene.sceneRoot.findViewById<ImageView>(R.id.imageViewThird)
                .load("https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")

            val valueAnimator = ValueAnimator().apply {
                duration = 1000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = LinearInterpolator()
                setFloatValues(1f, 1.05f)
            }

            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                scene.sceneRoot.findViewById<Layer>(R.id.layerFirst).scaleX = value
                scene.sceneRoot.findViewById<Layer>(R.id.layerFirst).scaleY = value
            }

            valueAnimator.start()

            fadeAnimation(
                scene.sceneRoot.findViewById<ImageView>(R.id.imageViewThird),
                scene.sceneRoot.findViewById<ImageView>(R.id.textViewThird),
                scene.sceneRoot.findViewById<ImageView>(R.id.textViewThirdPoint)
            )

            scene.sceneRoot.findViewById<Layer>(R.id.layerSecond).setOnClickListener {
                valueAnimator.end()
                performTransition()
            }
        }
        TransitionManager.go(scene, customTransition)
    }

    private fun transitionToSecond() {
        transitionStep = TransitionStep.SECOND

        val scene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.activity_second, activity)


        scene.setEnterAction {

            scene.sceneRoot.findViewById<ImageView>(R.id.imageViewSecondBig)
                .load("https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
            scene.sceneRoot.findViewById<ImageView>(R.id.imageClose).load(R.drawable.ic_close)

            ObjectAnimator().apply {
                target = scene.sceneRoot.findViewById<ImageView>(R.id.imageClose)
                duration = 2000
                repeatCount = ObjectAnimator.INFINITE
                interpolator = LinearInterpolator()
                setValues(PropertyValuesHolder.ofFloat("rotation", 0f, 360f))
                start()
            }

            scene.sceneRoot.findViewById<ImageView>(R.id.imageClose).setOnClickListener {
                performTransition()
            }
            scene.sceneRoot.findViewById<TextView>(R.id.textViewClose).setOnClickListener {
                performTransition()
            }
        }

        TransitionManager.go(scene, customTransition)
    }

    private fun fadeAnimation(vararg views: View) {

        for (view in views) {
            ObjectAnimator().apply {
                target = view
                duration = 1500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = AccelerateInterpolator()
                setValues(PropertyValuesHolder.ofFloat("alpha", 0f, 1f))
                start()
            }
        }
    }
}
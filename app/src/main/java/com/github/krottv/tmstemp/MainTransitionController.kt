package com.github.krottv.tmstemp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Group
import androidx.transition.ChangeBounds
import androidx.transition.Scene
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainTransitionController() {
    fun launchFirstScene(activityMain: ActivityMainBinding) {
        val firstScene = Scene.getSceneForLayout(activityMain.rootScene, R.layout.first_scene, this)

        val customTransition = TransitionSet().apply {
            addTransition(ChangeBounds())
        }

        TransitionManager.go(firstScene, customTransition)

        findViewById<ImageView>(R.id.imageView).load("https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
        findViewById<ImageView>(R.id.imageSecondParagraph).load("https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")
        findViewById<ImageView>(R.id.imageView3).load("https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80")

        ObjectAnimator().apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f),
            )
            target = findViewById<Layer>(R.id.layer)
            start()
        }

        firstScene.sceneRoot.findViewById<Group>(R.id.click_group).setOnClickListener {
            launchSecondScene(activityMain)
        }

        ObjectAnimator().apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
            )
            target = firstScene.sceneRoot.findViewById<ImageView>(R.id.imageView3)
            start()
        }

        /* ValueAnimator().apply {
             duration = 2000L
             repeatCount = ObjectAnimator.INFINITE
             repeatMode = ObjectAnimator.REVERSE
             setFloatValues(1f, 0f)
             addUpdateListener {
                 val value = it.animatedValue as Float
                 //findViewById<Layer>(R.id.layer2).alpha = value
                 firstScene.sceneRoot.findViewById<ImageView>(R.id.imageView3).alpha = value
                 firstScene.sceneRoot.findViewById<TextView>(R.id.textView5).alpha = value
                 firstScene.sceneRoot.findViewById<TextView>(R.id.textView6).alpha = value
             }
             start()
         }*/
    }

    fun loadData(item: Int, url: String) {
        findViewById<ImageView>(item).load(url)
    }

    fun launchSecondScene(activityMain: ActivityMainBinding) {
        val secondScene = Scene.getSceneForLayout(
            activityMain.rootScene,
            R.layout.second_paragraph_second_scene,
            this
        )
        val customTransition = TransitionSet().apply {
            addTransition(ChangeBounds())
        }
        TransitionManager.go(secondScene, customTransition)

        loadData(
            R.id.imageSecondParagraph,
            "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )

        secondScene.sceneRoot.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
            Log.i("test", "click2")
            launchFirstScene(activityMain)
        }


        ObjectAnimator().apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            setValues(
                PropertyValuesHolder.ofFloat("rotation", 0f, 360f)
            )
            target = findViewById<Layer>(R.id.imageView4)
            start()
        }
    }
}
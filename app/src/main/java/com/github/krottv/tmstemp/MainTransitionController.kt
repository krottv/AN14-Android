package com.github.krottv.tmstemp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.widget.ImageView
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Group
import androidx.transition.ChangeBounds
import androidx.transition.Scene
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainTransitionController(val activity: Activity) {
    private val customTransition = TransitionSet().apply {
        addTransition(ChangeBounds())
    }

    private val loadImage = LoadImage(activity)

    private fun launchObjectAnimation(item: Int, scene: Scene) {
        ObjectAnimator().apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
            )
            target = scene.sceneRoot.findViewById<ImageView>(item)
            start()
        }
    }

    fun launchFirstScene(activityMain: ActivityMainBinding) {
        val firstScene =
            Scene.getSceneForLayout(activityMain.rootScene, R.layout.first_scene, activity)

        TransitionManager.go(firstScene, customTransition)

        loadImage.loadData(
            R.id.imageView,
            "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )
        loadImage.loadData(
            R.id.imageSecondParagraph,
            "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )
        loadImage.loadData(
            R.id.imageView3,
            "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )

        ObjectAnimator().apply {
            duration = 2000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f),
            )
            target = activity.findViewById<Layer>(R.id.layer)
            start()
        }

        firstScene.sceneRoot.findViewById<Group>(R.id.click_group).setOnClickListener {
            launchSecondScene(activityMain)
        }

        launchObjectAnimation(R.id.imageView3, firstScene)
        launchObjectAnimation(R.id.textView5, firstScene)
        launchObjectAnimation(R.id.textView6, firstScene)

        //Выдает ошибку при переходе на другу сцену
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


    fun launchSecondScene(activityMain: ActivityMainBinding) {
        val secondScene = Scene.getSceneForLayout(
            activityMain.rootScene,
            R.layout.second_paragraph_second_scene,
            activity
        )

        TransitionManager.go(secondScene, customTransition)

        loadImage.loadData(
            R.id.imageSecondParagraph,
            "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )

        secondScene.sceneRoot.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
            launchFirstScene(activityMain)
        }


        ObjectAnimator().apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            setValues(
                PropertyValuesHolder.ofFloat("rotation", 0f, 360f)
            )
            target = activity.findViewById<Layer>(R.id.imageView4)
            start()
        }
    }
}
package com.github.krottv.tmstemp

import android.app.Activity
import android.view.LayoutInflater
import com.github.krottv.tmstemp.databinding.ActivityMainBinding

class MainActivityLayoutTransition(val activity: Activity) {
    fun bind() {
        val activityMain = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(activityMain.root)

        val mainTransitionController = MainTransitionController()

        mainTransitionController.launchFirstScene(activityMain)
    }
}
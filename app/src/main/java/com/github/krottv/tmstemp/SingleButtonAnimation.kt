package com.github.krottv.tmstemp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import java.util.*

class SingleButtonAnimation {
    fun animate(view: View) {
        val objectAnimator = ObjectAnimator()
        objectAnimator.duration = 1000L
        objectAnimator.repeatCount = 3
        objectAnimator.target = view
        objectAnimator.setValues(PropertyValuesHolder.ofFloat("rotation", 0f, 360f))
        objectAnimator.start()
    }
}
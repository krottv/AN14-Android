package com.github.krottv.tmstemp.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.view.View

class InterpolatorShowcase {
    fun startAnimation(view: View, root: View, interpolator: TimeInterpolator) {

        val toEnd = root.width - (view.width.toFloat() * 1.5f)

        ObjectAnimator().apply {
            target = view
            duration = 1000
            this.interpolator = interpolator
            setValues(PropertyValuesHolder.ofFloat("translationX", 0f, toEnd))
            start()
        }
    }
}
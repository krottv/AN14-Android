package com.github.krottv.tmstemp.animation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.core.animation.addListener

class SingleButtonAnimation {
    private var objectAnimator: ObjectAnimator? = null

    fun animate(view: View) {
        if (objectAnimator != null) {
            return
        }

        objectAnimator = ObjectAnimator().apply {
            duration = 1000L
            repeatCount = 3
            target = view
            setValues(PropertyValuesHolder.ofFloat("rotation", 0f, 360f))
            addListener(onEnd = {
                objectAnimator = null
            })
            start()
        }
    }
}
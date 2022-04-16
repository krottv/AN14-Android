package com.github.krottv.tmstemp.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues

class BackgroundCustomTransition : Transition() {

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {


        val firstColor = startValues?.values?.get(BACKGROUND_KEY) as? Int ?: Color.TRANSPARENT
        val endColor = endValues?.values?.get(BACKGROUND_KEY) as? Int ?: Color.TRANSPARENT

        if (firstColor == endColor || endValues == null) {
            return super.createAnimator(sceneRoot, startValues, endValues)
        } else {
            return ObjectAnimator.ofArgb(endValues.view, "backgroundColor", firstColor, endColor)
        }
    }

    private fun captureValues(transitionValues: TransitionValues) {

        val bg = transitionValues.view.background
        val color = (bg as? ColorDrawable)?.color ?: Color.TRANSPARENT
        transitionValues.values[BACKGROUND_KEY] = color
    }
}

private const val BACKGROUND_KEY = "app.some:BackgroundCustomTransition:backgroundColor"
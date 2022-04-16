package com.github.krottv.tmstemp.animation
import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View

class ElementsAnimationHelper {

    fun animateScaleEndlessly(view: View) {
        animateScaleEndlesslyObjectAnimator(view)
    }

    private fun ValueAnimator.prepareAnimator() {
        duration = 800
        repeatCount = ObjectAnimator.INFINITE
        repeatMode = ObjectAnimator.REVERSE
    }

    private fun animateScaleEndlesslyValueAnimator(view: View) {
        val valueAnimator = ValueAnimator().apply {
            prepareAnimator()
            setFloatValues(SCALE_BUTTON_FROM, SCALE_BUTTON_TO)
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            view.scaleX = value
            view.scaleY = value
        }
        valueAnimator.start()
    }

    private fun useAnimatorSet(view: View) {

        val scale = ObjectAnimator().apply {
            duration = 800
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", SCALE_BUTTON_FROM, SCALE_BUTTON_TO),
                PropertyValuesHolder.ofFloat("scaleY", SCALE_BUTTON_FROM, SCALE_BUTTON_TO),
            )
            target = view
        }

        val keyFrame1 = Keyframe.ofFloat(0f, 0f)
        val keyFrame2 = Keyframe.ofFloat(0.5f, 360f)
        val keyFrame3 = Keyframe.ofFloat(1f, 0f)

        val rotate = ObjectAnimator().apply {
            setValues(PropertyValuesHolder
                .ofKeyframe("rotation", keyFrame1, keyFrame2, keyFrame3))
            duration = 800
            target = view
        }

        AnimatorSet().apply {
            play(scale).with(rotate)
        }.start()
    }

    private fun animateScaleEndlesslyObjectAnimator(view: View) {

        val objectAnimator = ObjectAnimator().apply {
            prepareAnimator()
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", SCALE_BUTTON_FROM, SCALE_BUTTON_TO),
                PropertyValuesHolder.ofFloat("scaleY", SCALE_BUTTON_FROM, SCALE_BUTTON_TO),
            )
            target = view
        }
        objectAnimator.start()
    }
}

private const val SCALE_BUTTON_FROM = 1f
private const val SCALE_BUTTON_TO = 1.2f
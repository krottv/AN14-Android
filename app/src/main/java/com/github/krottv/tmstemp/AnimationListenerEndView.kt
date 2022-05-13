package com.tms.android.ffthtask

import android.view.View
import android.view.animation.Animation
import com.github.ybq.android.spinkit.SpinKitView

class AnimationListenerEndView (val progressBar: SpinKitView): Animation.AnimationListener {

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        progressBar.visibility = View.GONE
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }
}
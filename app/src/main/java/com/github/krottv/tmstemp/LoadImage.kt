package com.github.krottv.tmstemp

import android.app.Activity
import android.widget.ImageView
import coil.load

class LoadImage(val activity: Activity) {
    fun loadData(item: Int, url: String) {
        activity.findViewById<ImageView>(item).load(url)
    }
}
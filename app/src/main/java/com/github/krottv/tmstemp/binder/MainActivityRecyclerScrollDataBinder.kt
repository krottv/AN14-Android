package com.github.krottv.tmstemp.binder

import android.app.Activity
import android.view.LayoutInflater
import com.github.krottv.tmstemp.databinding.ActivityMainBinding


class MainActivityRecyclerScrollDataBinder(private val activity: Activity): MainActivityDataBinder {

    override fun bind() {
        val container = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        activity.setContentView(container.root)
    }

}
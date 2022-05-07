package com.github.krottv.tmstemp.binder
import android.app.Activity
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.krottv.tmstemp.databinding.ActivityMainBinding


class MainActivityRecyclerScrollDataBinder(private val activity: Activity): MainActivityDataBinder {

    override fun bind() {
        val container = ActivityMainBinding.inflate(LayoutInflater.from(activity))

        val navController = container.navHostFragment.getFragment<Fragment>().findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        container.toolbar.setupWithNavController(navController, appBarConfiguration)
        activity.setContentView(container.root)
    }

}
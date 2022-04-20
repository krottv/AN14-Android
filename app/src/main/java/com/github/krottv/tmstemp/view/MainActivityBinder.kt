package com.github.krottv.tmstemp.view

import android.app.Activity
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.krottv.tmstemp.databinding.ActivityMainBinding
import com.github.krottv.tmstemp.domain.PostModel
import okhttp3.internal.notify

class MainActivityBinder(val activity: Activity) {
    lateinit var binding: ActivityMainBinding
    fun bindView() {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(activity))
        val layoutManager = LinearLayoutManager(activity)
        activity.setContentView(binding.root)
        binding.recyclerView.layoutManager = layoutManager
        binding.textViewDelete.text

    }

    fun applyAdapter(list: MutableList<PostModel>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        View.VISIBLE.also { binding.textViewDelete.visibility = it }
        binding.recyclerView.adapter = PostAdapter(list)
        binding.textViewDelete.setOnClickListener {

        }
    }

    fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.textViewDelete.visibility = View.GONE
    }

}
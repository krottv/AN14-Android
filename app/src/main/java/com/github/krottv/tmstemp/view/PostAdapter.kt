package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel

class PostAdapter(private var data: MutableList<PostModel>) :
    RecyclerView.Adapter<PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = data[position]
        holder.image.load(item.image)
        holder.imageMini.load(item.imageMini)
        holder.textView.text = item.title
        holder.textViewSub.text = item.subtitle
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
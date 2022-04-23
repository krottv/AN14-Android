package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel


class PostsAdapter(var data: List<PostModel>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val cell = data[position]
        holder.image.apply {
            load(cell.image)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        holder.imageMini.apply {
            load(cell.imageMini)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        holder.textViewSub.text = cell.subtitle
        holder.textView.text = cell.title
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeOne() {
        if (itemCount > 0) {
            data = data.subList(1, itemCount)
            notifyItemRemoved(0)
        }
    }
}


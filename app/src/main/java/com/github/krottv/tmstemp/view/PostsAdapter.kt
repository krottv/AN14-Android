package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel

class PostsAdapter(data: List<PostModel>) : RecyclerView.Adapter<PostViewHolder>() {
    var data: MutableList<PostModel> = data as MutableList<PostModel>

    fun removeFirstItem() {
        data.removeAt(0)
        notifyItemRemoved(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = data[position]
        holder.image.load(item.image)
        holder.textView.text = item.title
        holder.textView2.text = item.subtitle
        holder.image2.load(item.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
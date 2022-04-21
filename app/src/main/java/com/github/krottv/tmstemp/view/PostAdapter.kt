package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel

class PostAdapter(
    data: List<PostModel>,
    private val onItemClick: (View, PostModel) -> Unit
) : RecyclerView.Adapter<PostViewHolder>() {

    private var data: List<PostModel> = data
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = data[position]
        holder.image.load(item.image)
        holder.textView.text = item.title
        holder.textViewSub.text = item.subtitle

        holder.itemView.transitionName = item.transitionId()
        holder.itemView.setOnClickListener {
            onItemClick(it, item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
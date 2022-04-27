package com.github.krottv.tmstemp.view

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.PostModel

class PostsAdapter(data: List<PostModel>, private val onItemClick: (View, PostModel) -> Unit) :
    RecyclerView.Adapter<PostViewHolder>() {
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

        holder.image.clipToOutline = true
        holder.image.outlineProvider = object: ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setOval(0, 0, view.width, view.height)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick(it, item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
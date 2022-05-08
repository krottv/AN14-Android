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
        holder.image.load(item.image) {
            holder.image.clipToOutline = true
            holder.image.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    val radius = view.height / 2
                    val centre = view.width / 2
                    outline.setRoundRect(
                        centre - radius, 0,
                        centre + radius, view.height,
                        radius.toFloat()
                    )
                }
            }
            holder.image.elevation = 25f
        }
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



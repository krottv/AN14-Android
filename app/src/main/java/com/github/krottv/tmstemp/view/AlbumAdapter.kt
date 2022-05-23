package com.github.krottv.tmstemp.view

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.AlbumModel

class AlbumAdapter(data: List<AlbumModel>, private val onItemClick: (Long) -> Unit): RecyclerView.Adapter<AlbumViewHolder>() {

    var data: List<AlbumModel> = data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_element, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val cell = data[position]

        holder.albumImage.apply {
            load(cell.image) {
                placeholder(R.drawable.loading_1)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            clipToOutline = true
            outlineProvider = object: ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(0,0,view.width, view.height, 30f)
                }
            }
        }

        holder.albumImageFrame.apply {
            setBackgroundResource(R.color.colorPrimary)

            clipToOutline = true
            outlineProvider = object: ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(0, 0, view.width, view.height, 34f)
                }
            }
        }
        holder.itemView.setOnClickListener {
            onItemClick(position+1.toLong())
        }

        holder.albumName.text = cell.name
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
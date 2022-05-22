package com.github.krottv.tmstemp.view

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.AlbumModel

class AlbumsAdapter(data: List<AlbumModel>) : RecyclerView.Adapter<AlbumsViewHolder>() {

    var data: List<AlbumModel> = data
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = data[position]
        holder.imageAlbum.load(item.image)
        holder.textAlbum.text = item.name

        holder.imageAlbum.clipToOutline = true
        holder.imageAlbum.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 24.0F)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
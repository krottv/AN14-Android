package com.github.krottv.tmstemp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks

class AlbumsAdapter(data: List<AlbumModel>): RecyclerView.Adapter<AlbumsViewHolder>() {

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
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
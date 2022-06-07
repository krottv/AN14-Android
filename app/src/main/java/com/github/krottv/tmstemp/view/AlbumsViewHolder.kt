package com.github.krottv.tmstemp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class AlbumsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageAlbum: ImageView = view.findViewById(R.id.imageAlbum)
    val textAlbum: TextView = view.findViewById(R.id.albumText)
    val albumImageFrame: ImageView = view.findViewById(R.id.albumImageFrame)
}
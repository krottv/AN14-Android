package com.github.krottv.tmstemp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.imagePost)
    val textView: TextView = view.findViewById(R.id.textTitle)
    val textViewSub: TextView = view.findViewById(R.id.textSubtitle)
}

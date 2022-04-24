package com.github.krottv.tmstemp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.findViewById<ImageView>(R.id.imageView)
    val textView = view.findViewById<TextView>(R.id.textView)
    val textView2 = view.findViewById<TextView>(R.id.textView2)
}
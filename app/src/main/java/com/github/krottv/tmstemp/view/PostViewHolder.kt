package com.github.krottv.tmstemp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image1 = view.findViewById<ImageView>(R.id.imageView1)
    val textView1 = view.findViewById<TextView>(R.id.textView1)
    val textView2 = view.findViewById<TextView>(R.id.textView2)
    val image2 = view.findViewById<ImageView>(R.id.imageView2)
}
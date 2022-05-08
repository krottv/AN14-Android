package com.github.krottv.tmstemp.view
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.R

class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val mainImage: ImageView = view.findViewById(R.id.userImage)
    val mainText: TextView = view.findViewById(R.id.userText)
    val littleText: TextView = view.findViewById(R.id.textFragment)
}
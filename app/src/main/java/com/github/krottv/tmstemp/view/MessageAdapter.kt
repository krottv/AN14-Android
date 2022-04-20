package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.Message

class MessageAdapter(var data: List<Message>): RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_cell, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val cell = data[position]
        holder.mainImage.load(cell.mainImage)
        holder.littleImage.load(cell.littleImage)
        holder.mainText.text = cell.mainText
        holder.littleText.text = cell.littleText
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
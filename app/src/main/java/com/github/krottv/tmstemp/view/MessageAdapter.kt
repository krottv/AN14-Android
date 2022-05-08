package com.github.krottv.tmstemp.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.domain.MessageModel

class MessageAdapter(var data: List<MessageModel>, private val onItemClick: (View, MessageModel) -> Unit): RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_scene, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val cell = data[position]
        holder.mainImage.apply {
            load(cell.mainImage)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        holder.mainText.text = cell.mainText
        holder.littleText.text = cell.littleText

        holder.itemView.transitionName = cell.transitionId()

        holder.itemView.setOnClickListener {
            onItemClick(it, cell)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
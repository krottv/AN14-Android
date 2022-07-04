package com.example.app.presentation.view.tracksrecycler

import android.content.res.Resources
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.app.domain.TrackModel
import com.example.app.view.R

class TracksAdapter(
    var data: List<TrackModel>,
    private val onItemClick: (View, TrackModel) -> Boolean
) : RecyclerView.Adapter<TracksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)

        return TracksViewHolder(view)
    }

    private fun dpToPx(dp: Int): Float = dp * Resources.getSystem().displayMetrics.density

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val item = data[position]
        holder.imageTrack.apply {
            load(item.image.replace("{w}", "200").replace("{h}", "200"))
            clipToOutline = true
            outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(p0: View, p1: Outline) {
                    p1.setRoundRect(0, 0, p0.width, p0.height, dpToPx(10))
                }
            }
        }

        holder.titleTrack.text = item.title
        holder.textTrack.text = item.artist
        holder.itemView.setOnLongClickListener {
            onItemClick(it, item)
            return@setOnLongClickListener true
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

}
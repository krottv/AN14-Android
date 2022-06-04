package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.krottv.tmstemp.databinding.MyMusicFragmentBinding
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel

class MyMusicFragmentBinder(val fragment: MyMusicFragment, val onItemClick: (View, Tracks) -> Boolean) {

    lateinit var binding: MyMusicFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        binding = MyMusicFragmentBinding.inflate(inflater, container, false)

        var layoutManager =
            LinearLayoutManager(fragment.requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.albumsRecyclerMyMusic.layoutManager = layoutManager

        layoutManager =
            LinearLayoutManager(fragment.requireActivity())
        binding.tracksRecyclerMyMusic.layoutManager = layoutManager

        return binding.root
    }

    fun onDataLoaded(list: List<AlbumModel>?) {

        if (list != null)
            if (binding.albumsRecyclerMyMusic.adapter == null)
                binding.albumsRecyclerMyMusic.adapter = AlbumsAdapter(list)
            else
                (binding.albumsRecyclerMyMusic.adapter as AlbumsAdapter).data = list
    }

    fun tracksLoaded(list: TracksModel?) {

        if (list != null) {
            if (binding.tracksRecyclerMyMusic.adapter == null)
                binding.tracksRecyclerMyMusic.adapter = TracksAdapter(list.tracks, onItemClick)
            else
                (binding.tracksRecyclerMyMusic.adapter as TracksAdapter).data = list.tracks
        }
    }
}
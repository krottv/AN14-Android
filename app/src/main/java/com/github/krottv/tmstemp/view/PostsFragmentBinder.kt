package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.github.krottv.tmstemp.databinding.PostsFragmentBinding
import com.github.krottv.tmstemp.domain.PostModel

class PostsFragmentBinder(private val fragment: Fragment,
                          private val onItemClick: (View, PostModel) -> Unit) {

    private lateinit var binding: PostsFragmentBinding

    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = PostsFragmentBinding.inflate(inflater, container, false)


        val layoutManger = LinearLayoutManager(fragment.requireActivity(), VERTICAL, false)

        binding.recyclerView.layoutManager = layoutManger


        return binding.root
    }

    fun onDataLoaded(list: List<PostModel>) {
        if (binding.recyclerView.adapter == null) {
            binding.recyclerView.adapter = PostsAdapter(list, onItemClick)
        } else {
            (binding.recyclerView.adapter as PostsAdapter).data = list as MutableList<PostModel>
        }
    }
}
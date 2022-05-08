package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.krottv.tmstemp.databinding.PostsFragmentBinding
import com.github.krottv.tmstemp.domain.PostModel

class PostsFragmentBinder(
    private val fragment: Fragment,
    private val onItemClick: (View, PostModel) -> Unit
) {
    private lateinit var binding: PostsFragmentBinding
    fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostsFragmentBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(fragment.requireActivity())

        binding.recyclerView.layoutManager = layoutManager
        return binding.root
    }

    fun applyAdapter(list: List<PostModel>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.recyclerView.adapter = PostAdapter(list, onItemClick)
    }

    fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE

    }


}
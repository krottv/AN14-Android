package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.krottv.tmstemp.presentation.PostsViewModel
import kotlinx.coroutines.launch

class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binder: PostsFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binder = PostsFragmentBinder(this) { view, post ->
            findNavController().navigate(
                PostsFragmentDirections.actionPostsFragmentToFullscreenFragment(
                    post.title,
                    post
                )
            )
        }
        return binder.bindView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition(1000L, java.util.concurrent.TimeUnit.MILLISECONDS)
        viewModel.loadData()

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect {
                    if (it != null) {
                        if (it.isSuccess) {

                            binder.applyAdapter(it.getOrThrow())
                        }
                        if (it.isFailure) {
                            binder.showProgress()
                        }
                    }
                }
            }
        }
    }
}
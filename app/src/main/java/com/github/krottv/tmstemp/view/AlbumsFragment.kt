package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.krottv.tmstemp.binder.AlbumFragmentBinder
import com.github.krottv.tmstemp.domain.ContentType
import com.github.krottv.tmstemp.presentation.AlbumViewModel
import com.github.krottv.tmstemp.presentation.SongViewModel
import kotlinx.coroutines.launch

class AlbumsFragment: Fragment() {

    private lateinit var fragment: AlbumFragmentBinder
    private val viewModel: AlbumViewModel by activityViewModels()
    private val songViewModel: SongViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragment = AlbumFragmentBinder(this) {
            songViewModel.loadData(it, requireArguments().get("contentType") as ContentType)
        }

        return fragment.bindView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData(requireArguments().get("contentType") as ContentType)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if (it != null) {
                        if (it.isSuccess) {
                            fragment.onDataLoaded(it.getOrThrow())
                        }
                    }
                }
            }
        }
    }
}
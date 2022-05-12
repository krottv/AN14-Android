package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.data.LibraryMusicApi
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.TracksModel
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.LibraryAlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksITunesViewModel
import com.github.krottv.tmstemp.presentation.TracksLibraryViewModel
import kotlinx.coroutines.launch

class LibraryMusicFragment : Fragment() {
    lateinit var viewBinder: LibraryMusicFragmentBinder
    var viewModel: LibraryAlbumsViewModel = LibraryAlbumsViewModel(LibraryRemoteDataSourceRetrofit())
    var tracksLibraryViewModel: TracksLibraryViewModel = TracksLibraryViewModel(LibraryRemoteDataSourceRetrofit())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinder = LibraryMusicFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes)?.setOnClickListener{
            val navController = findNavController()

            val action = LibraryMusicFragmentDirections.actionLibraryMusicFragmentToITunesMusicFragment()
            navController.navigate(action)
        }

        viewModel.loadDataLibrary()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    viewBinder.onDataLoaded(it)
                }
            }
        }

        tracksLibraryViewModel.loadTracksLibrary()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksLibraryViewModel.stateITunes.collect {
                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
}
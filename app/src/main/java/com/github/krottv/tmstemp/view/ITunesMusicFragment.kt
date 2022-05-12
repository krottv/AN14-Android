package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.util.Log
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
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksITunesViewModel
import kotlinx.coroutines.launch

class ITunesMusicFragment : Fragment() {
    lateinit var viewBinder: ITunesMusicFragmentBinder
    var viewModel: AlbumsViewModel = AlbumsViewModel(ITunesRemoteDataSourceRetrofit())
    var tracksITunesViewModel: TracksITunesViewModel = TracksITunesViewModel(
        ITunesRemoteDataSourceRetrofit()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinder = ITunesMusicFragmentBinder(this)
        val currentView: View = viewBinder.onCreateView(inflater, container, savedInstanceState)

        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    ITunesMusicFragmentDirections.actionITunesMusicFragmentToLibraryMusicFragment()
                navController.navigate(action)
            }

        viewModel.loadDataITunes()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    viewBinder.onDataLoaded(it)
                }
            }
        }

        tracksITunesViewModel.loadTracksITunes()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksITunesViewModel.stateITunes.collect {

                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
}
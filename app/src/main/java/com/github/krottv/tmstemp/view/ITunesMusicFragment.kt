package com.github.krottv.tmstemp.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.github.krottv.tmstemp.R
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ITunesMusicFragment : Fragment() {

    lateinit var viewBinder: ITunesMusicFragmentBinder
    private val viewModel: AlbumsViewModel by inject()
    private val tracksITunesViewModel: TracksViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = ITunesMusicFragmentBinder(this)
        return viewBinder.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    ITunesMusicFragmentDirections.actionITunesMusicFragmentToLibraryMusicFragment()
                navController.navigate(action)
                changeCurrentSelection(
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic) as TextView,
                )
            }
        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    ITunesMusicFragmentDirections.actionITunesMusicFragmentToMyMusicFragment()
                navController.navigate(action)
                changeCurrentSelection(
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.myMusic) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.library) as TextView,
                    (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.iTunes) as TextView,
                )

            }

        (parentFragment as NavHostFragment).parentFragment?.view?.findViewById<View>(R.id.imagePurchase)
            ?.setOnClickListener {
                val navController = findNavController()

                val action =
                    ITunesMusicFragmentDirections.actionITunesMusicFragmentToPurchaseFragment()
                navController.navigate(action)


            }
        viewModel.loadData()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateITunes.collect {
                    viewBinder.onDataLoaded(it)
                }
            }
        }

        tracksITunesViewModel.loadTracks()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksITunesViewModel.stateITunes.collect {

                    viewBinder.tracksLoaded(it)
                }
            }
        }
    }
  private fun changeCurrentSelection(primary: TextView, secondary: TextView, three: TextView ) {
        primary.typeface = Typeface.DEFAULT_BOLD
        primary.textSize = 18f
        primary.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextColor))
        primary.isClickable = false

        secondary.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        secondary.textSize = 16f
        secondary.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        secondary.isClickable = true

        three.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        three.textSize = 16f
        three.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        three.isClickable = true
    }
}


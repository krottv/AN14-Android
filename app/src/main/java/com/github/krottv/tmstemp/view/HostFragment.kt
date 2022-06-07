package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HostFragment : Fragment() {

    lateinit var viewBinder: HostFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = HostFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container)
    }
}


/*
class HostFragment: Fragment() {

    private lateinit var fragment: HostFragmentBinding
    private val albumsFragment = LibraryMusicFragment()
    private val songsFragment = ITunesMusicFragment()
    private val albumViewModel by sharedViewModel <AlbumsViewModel>()
    private val songViewModel by sharedViewModel <TracksViewModel>()
    private val songFragmentBundle = Bundle()
    private val albumFragmentBundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragment = HostFragmentBinding.inflate(inflater)


        songFragmentBundle.putLong("albumId", 1)
        songFragmentBundle.putSerializable("contentType", ContentType.ITUNES
        )
        songsFragment.arguments = songFragmentBundle

        albumFragmentBundle.putSerializable("contentType", ContentType.ITUNES
        )
        albumsFragment.arguments = albumFragmentBundle

        openFrag(albumsFragment, R.id.albumsRecyclerITunes)
        openFrag(songsFragment, R.id.tracksRecyclerITunes)


        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment.iTunes.setOnClickListener {
            albumFragmentBundle.putSerializable("contentType", ContentType.ITUNES
            )
            albumViewModel.loadData(ContentType.ITUNES)
            songViewModel.loadTracks(AlbumType(1, ContentType.ITUNES))
            changeCurrentSelection(fragment.iTunes, fragment.library)
        }

        fragment.library.setOnClickListener {
            albumFragmentBundle.putSerializable("contentType", ContentType.LIBRARY
            )
            albumViewModel.loadData(ContentType.LIBRARY)
            songViewModel.loadTracks(AlbumType(1, ContentType.LIBRARY
            ))
            changeCurrentSelection(fragment.library, fragment.iTunes)
        }

        changeCurrentSelection(fragment.iTunes, fragment.library)
    }

    private fun openFrag(fragment: Fragment, idHolder: Int) {
        parentFragmentManager
            .beginTransaction()
            .replace(idHolder, fragment)
            .commit()
    }

    private fun changeCurrentSelection(primary: TextView, secondary: TextView) {
        primary.typeface = Typeface.DEFAULT_BOLD
        primary.textSize = 18f
        primary.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextColor))
        primary.isClickable = false

        secondary.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        secondary.textSize = 16f
        secondary.setTextColor(ContextCompat.getColor(requireContext(), R.color.textColor))
        secondary.isClickable = true
    }
}*/

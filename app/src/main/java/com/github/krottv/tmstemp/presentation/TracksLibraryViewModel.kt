package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.LibraryMusicApi
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.TracksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksLibraryViewModel(val libraryMusicApi: LibraryMusicApi): ViewModel() {
    private val _stateITunes = MutableStateFlow<TracksModel?>(null)
    val stateITunes: StateFlow<TracksModel?> = _stateITunes

    fun loadTracksLibrary() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(libraryMusicApi.getLibraryTracks(1))
        }
    }
}
package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksITunesViewModel(val iTunesMusicApi: ITunesRemoteDataSourceRetrofit): ViewModel() {
    private val _stateITunes = MutableStateFlow<TracksModel?>(null)
    val stateITunes: StateFlow<TracksModel?> = _stateITunes

    fun loadTracksITunes() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(iTunesMusicApi.getITunesTracks(1))
        }
    }
}
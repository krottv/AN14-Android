package com.example.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.remote.TracksMyMusicDataSource
import com.example.app.domain.TrackModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TracksMyMusicViewModel(val musicApi: TracksMyMusicDataSource): ViewModel() {

    private val _stateITunes = MutableStateFlow<List<TrackModel>?>(null)
    val stateITunes: StateFlow<List<TrackModel>?> = _stateITunes

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getTracks())
        }
    }
}
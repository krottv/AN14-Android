package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.remote.MusicApi
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TracksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyMusicViewModel(private val musicApi: MusicApi) : ViewModel() {

    private val _stateITunes = MutableStateFlow<List<AlbumModel>?>(null)
    val stateITunes: StateFlow<List<AlbumModel>?> = _stateITunes

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(musicApi.getAlbums())
        }
    }
    /*private val _stateMyMusic = MutableStateFlow<AlbumModel>(null)
    val stateITunes: StateFlow<AlbumModel> = _stateMyMusic

    fun loadTracks() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMyMusic.emit(musicApi.getTracks(1))
        }
    }*/
}
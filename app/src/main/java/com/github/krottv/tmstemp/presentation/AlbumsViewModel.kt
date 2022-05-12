package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsViewModel(val iTunesMusicApi: ITunesRemoteDataSourceRetrofit): ViewModel(){

    private val _stateITunes = MutableStateFlow<List<AlbumModel>?>(null)
    val stateITunes: StateFlow<List<AlbumModel>?> = _stateITunes

    fun loadDataITunes() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(iTunesMusicApi.getITunesAlbums())
        }
    }


}
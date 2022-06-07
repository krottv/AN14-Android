package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.remote.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(private val musicApi: RemoteDataSourceRetrofit) : ViewModel() {

    private val _stateITunes = MutableStateFlow<Result<List<AlbumModel>>?>(null)
    val stateITunes: StateFlow<Result<List<AlbumModel>>?> = _stateITunes
    private var downloadingJob: Job? = null

    fun loadData() {
        downloadingJob?.cancel()

        downloadingJob = viewModelScope.launch(Dispatchers.IO) {
            _stateITunes.emit(null)

            val result = try {
                Result.success(musicApi.getAlbums())

            } catch (t: Throwable) {
                t.printStackTrace()
                Result.failure(t)
            }

            _stateITunes.emit(result)
        }
    }
}

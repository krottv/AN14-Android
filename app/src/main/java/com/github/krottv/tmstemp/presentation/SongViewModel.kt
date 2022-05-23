package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.SongsRepository
import com.github.krottv.tmstemp.data.db.SongDbInMemoryDataSource
import com.github.krottv.tmstemp.data.remote.SongRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.ContentType
import com.github.krottv.tmstemp.domain.SongModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SongViewModel: ViewModel() {
    private val songRepository = SongsRepository(SongDbInMemoryDataSource(), SongDbInMemoryDataSource(), SongRemoteDataSourceRetrofit())

    private val _state = MutableStateFlow<Result<List<SongModel>>?>(null)
    val state: StateFlow<Result<List<SongModel>>?> = _state

    private var downloadingJob: Job? = null

    fun loadData(album_id : Long, contentType: ContentType) {
        downloadingJob?.cancel()

        downloadingJob = viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                when(contentType) {
                    ContentType.ITUNES -> Result.success(songRepository.getItunesSongs(album_id))
                    ContentType.LIBRARY -> Result.success(songRepository.getLibrarySongs(album_id))
                }
            } catch (exception: Throwable) {
                Result.failure(exception)
            }
            _state.emit(result)
        }
    }
}
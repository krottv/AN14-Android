package com.github.krottv.tmstemp.data.remote

import com.github.krottv.tmstemp.domain.SongsJSON

interface SongRemoteDataSource {
    suspend fun getItunesSongs(album_id : Long): SongsJSON
    suspend fun getLibrarySongs(album_id : Long): SongsJSON
}
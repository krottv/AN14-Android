package com.github.krottv.tmstemp.data.remote

import com.github.krottv.tmstemp.domain.SongsJSON

class SongRemoteDataSourceRetrofit : SongRemoteDataSource {

    override suspend fun getItunesSongs(album_id: Long): SongsJSON {
        return RetrofitBuilder.musicApi.getItunesSongs(album_id)
    }

    override suspend fun getLibrarySongs(album_id : Long): SongsJSON {
        return RetrofitBuilder.musicApi.getLibrarySongs(album_id)
    }
}

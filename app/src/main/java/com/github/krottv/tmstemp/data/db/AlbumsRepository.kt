package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.data.remote.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.AlbumModel


class AlbumsRepository(
    private val albumsDbDataSource: AlbumsDbDataSource,
    private val albumsRemoteDataSource: RemoteDataSourceRetrofit,
    private val isCacheEnabled: Boolean
) {

    suspend fun getAlbums(): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = albumsRemoteDataSource.getAlbums()
            albumsDbDataSource.saveAlbums(cache)
        }

        return cache
    }
}
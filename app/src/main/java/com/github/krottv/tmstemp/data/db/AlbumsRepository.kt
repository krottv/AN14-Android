package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.data.remote.AlbumRemoteDataSource
import com.github.krottv.tmstemp.domain.AlbumModel


class AlbumsRepository(
    private val albumsDbDataSource: AlbumDbDataSource,
    private val albumsRemoteDataSource: AlbumRemoteDataSource,
    private val isCacheEnabled: Boolean
) {

    suspend fun getData(): List<AlbumModel> {

        var cache = if (isCacheEnabled) albumsDbDataSource.getAlbums() else null
        if (cache.isNullOrEmpty()) {
            cache = albumsRemoteDataSource.getAlbums()
            albumsDbDataSource.saveAlbums(cache)
        }

        return cache
    }
}
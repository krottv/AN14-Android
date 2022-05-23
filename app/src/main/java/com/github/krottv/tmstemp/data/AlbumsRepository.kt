package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.data.db.AlbumDbDataSource
import com.github.krottv.tmstemp.data.remote.AlbumRemoteDataSource
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.ContentType

class AlbumsRepository(private val albumDbDataSource: AlbumDbDataSource,
                       private val albumRemoteDataSource: AlbumRemoteDataSource) {

    suspend fun getItunesAlbums(): List<AlbumModel> {

        val cache = albumDbDataSource.getAlbums(ContentType.ITUNES)
        val response = albumRemoteDataSource.getItunesAlbums()

        if (cache == null) {
            albumDbDataSource.saveAlbums(ContentType.ITUNES, response)
            return response
        }

        return if (cache.contains(ContentType.ITUNES)) {
            cache[ContentType.ITUNES]!!
        } else {
            albumDbDataSource.saveAlbums(ContentType.ITUNES, response)
            response
        }
    }

    suspend fun getLibraryAlbums(): List<AlbumModel> {
        val cache = albumDbDataSource.getAlbums(ContentType.LIBRARY)
        val response = albumRemoteDataSource.getLibraryAlbums()

        if (cache == null) {
            albumDbDataSource.saveAlbums(ContentType.LIBRARY, response)
            return response
        }

        return if (cache.contains(ContentType.LIBRARY)) {
            cache[ContentType.LIBRARY]!!
        } else {
            albumDbDataSource.saveAlbums(ContentType.LIBRARY, response)
            response
        }
    }
}
package com.github.krottv.tmstemp.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel

interface AlbumRemoteDataSource {
    suspend fun getItunesAlbums(): List<AlbumModel>
    suspend fun getLibraryAlbums(): List<AlbumModel>
}
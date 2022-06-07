package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.domain.AlbumModel

interface AlbumsDbDataSource {
    suspend fun saveAlbums(list: List<AlbumModel>)
    suspend fun getAlbums(): List<AlbumModel>?
}

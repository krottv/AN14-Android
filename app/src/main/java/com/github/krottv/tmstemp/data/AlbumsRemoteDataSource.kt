package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.AlbumModel

interface AlbumsRemoteDataSource {
    suspend fun getAlbums(): List<AlbumModel>
}
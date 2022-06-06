package com.github.krottv.tmstemp.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel


interface AlbumRemoteDataSource {
    suspend fun getAlbums(): List<AlbumModel>
}
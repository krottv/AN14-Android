package com.github.krottv.tmstemp.data.remote

import com.github.krottv.tmstemp.domain.AlbumModel

class AlbumRemoteDataSourceRetrofit : AlbumRemoteDataSource {

    override suspend fun getItunesAlbums(): List<AlbumModel> {
        return RetrofitBuilder.musicApi.getItunesAlbums()
    }

    override suspend fun getLibraryAlbums(): List<AlbumModel> {
        return RetrofitBuilder.musicApi.getLibraryAlbums()
    }
}
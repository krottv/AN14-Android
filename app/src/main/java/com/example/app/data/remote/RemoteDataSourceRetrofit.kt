package com.example.app.data.remote

import com.example.app.domain.AlbumModel
import com.example.app.domain.TracksWithAlbums
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create


class RemoteDataSourceRetrofit() : RemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://stellio.ru/.inspiry/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build().create() as MusicApi

    override suspend fun getItunesAlbums(): List<AlbumModel> {
        return retrofit.getItunesAlbums()
    }

    override suspend fun getItunesTracks(albumId: Long): TracksWithAlbums {
        return retrofit.getItunesTracks(1)
    }

    override suspend fun getLibraryAlbums(): List<AlbumModel> {
        return retrofit.getLibraryAlbums()
    }

    override suspend fun getLibraryTracks(albumId: Long): TracksWithAlbums {
        return retrofit.getLibraryTracks(albumId)
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        return retrofit.downloadFile(fileUrl);
    }
}
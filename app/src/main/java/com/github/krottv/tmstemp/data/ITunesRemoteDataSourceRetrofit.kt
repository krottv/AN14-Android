package com.github.krottv.tmstemp.data

import android.util.Log
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

class ITunesRemoteDataSourceRetrofit : ITunesMusicApi {

    override suspend fun getITunesAlbums(): List<AlbumModel> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getItunesAlbums/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: ITunesMusicApi = retrofit.create()
        return musicApi.getITunesAlbums()
    }

    override suspend fun getITunesTracks(albumId: Long): TracksModel {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us-central1-inspiry-2ee60.cloudfunctions.net/getItunesTracks/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val musicApi: ITunesMusicApi = retrofit.create()

        return musicApi.getITunesTracks(1)
    }
}
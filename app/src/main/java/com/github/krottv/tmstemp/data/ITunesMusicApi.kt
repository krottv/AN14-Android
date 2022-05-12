package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.Tracks
import com.github.krottv.tmstemp.domain.TracksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesMusicApi {
    @GET("getITunesAlbums")
    suspend fun getITunesAlbums(): List<AlbumModel>

    @GET("getITunesTrack")
    suspend fun getITunesTracks(@Query("album_id") albumId: Long): TracksModel
}
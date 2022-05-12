package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TracksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryMusicApi {
    @GET("getLibraryAlbums")
    suspend fun getLibraryAlbums(): List<AlbumModel>

    @GET("getLibraryTrack")
    suspend fun getLibraryTracks(@Query("album_id") albumId: Long): TracksModel
}
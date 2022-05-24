package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.data.db.SongDbDataSource
import com.github.krottv.tmstemp.data.remote.SongRemoteDataSource
import com.github.krottv.tmstemp.domain.SongModel

class SongsRepository(private val songDbDataSourceItunes: SongDbDataSource,
                      private val songDbDataSourceLibrary: SongDbDataSource,
                      private val songRemoteDataSource: SongRemoteDataSource) {

    suspend fun getItunesSongs(album_id : Long): List<SongModel> {

        val cache = songDbDataSourceItunes.getSongs()
        val response = songRemoteDataSource.getItunesSongs(album_id)

        if (cache == null) {
            songDbDataSourceItunes.saveSongs(response)
            return response.tracks
        }

        return if (cache.contains(album_id)) {
            cache[album_id]!!
        } else {
            songDbDataSourceItunes.saveSongs(response)
            response.tracks
        }
    }

    suspend fun getLibrarySongs(album_id : Long): List<SongModel> {

        val cache = songDbDataSourceLibrary.getSongs()
        val response = songRemoteDataSource.getLibrarySongs(album_id)

        if (cache == null) {
            songDbDataSourceLibrary.saveSongs(response)
            return response.tracks
        }

        return if (cache.contains(album_id)) {
            cache[album_id]!!
        } else {
            songDbDataSourceLibrary.saveSongs(response)
            response.tracks
        }
    }
}
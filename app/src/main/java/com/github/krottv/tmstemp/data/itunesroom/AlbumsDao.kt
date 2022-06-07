package com.github.krottv.tmstemp.data.itunesroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.krottv.tmstemp.domain.AlbumModel
import kotlinx.coroutines.flow.Flow


@Dao
interface AlbumsDao {

    @Insert
    suspend fun saveAlbums(albums: List<AlbumModel>)

    @Query("SELECT * FROM albums order by name asc")
    suspend fun getAlbums(): List<AlbumModel>

    @Query("select * from albums where id = :albumId")
    fun findAlbumById(albumId: Long): Flow<AlbumModel?>

    @Transaction
    suspend fun savesAlbumsTenTime(albums: List<AlbumModel>) {
        for (i in 1..10) {
            saveAlbums(albums)
        }
    }

    @Query("select * from albums where id = :albumId")
    suspend fun getAlbumWithTracks(albumId: Long): ITunesTracksWithAlbumEntity
}
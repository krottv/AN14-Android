package com.github.krottv.tmstemp.data.room

import androidx.room.Dao
import androidx.room.Query
import com.github.krottv.tmstemp.domain.TrackModel
import com.github.krottv.tmstemp.domain.TracksModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TracksDao {

    @Query("select * from tracks where albumId = :albumId")
    fun getTracksByAlbumId(albumId: Long): Flow<List<TrackModel>>
}
package com.github.krottv.tmstemp.data.libraryroom

import androidx.room.Embedded
import androidx.room.Relation
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TrackModel


data class LibraryTracksWithAlbumEntity(
    @Embedded val album: AlbumModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "albumId"
    )
    val tracks: List<TrackModel>
)
package com.github.krottv.tmstemp.data.libraryroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.krottv.tmstemp.domain.AlbumModel
import com.github.krottv.tmstemp.domain.TrackModel

@Database(entities = [AlbumModel::class, TrackModel::class], version = 1)
abstract class LibraryDatabase: RoomDatabase() {

    abstract fun provideDao(): LibraryAlbumsDao
    abstract fun provideDaoTracks(): LibraryTracksDao
}
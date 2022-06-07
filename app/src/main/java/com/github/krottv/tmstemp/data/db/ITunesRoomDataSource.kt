package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.data.itunesroom.MyDatabase
import com.github.krottv.tmstemp.data.libraryroom.LibraryDatabase
import com.github.krottv.tmstemp.domain.AlbumModel

class ITunesRoomDataSource(database: MyDatabase): AlbumsDbDataSource {

    private val dao = database.provideDao()

    override suspend fun saveAlbums(list: List<AlbumModel>) {
        dao.saveAlbums(list)
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return dao.getAlbums().map { it.copy(name = it.name + "_iTunes") }
    }
}
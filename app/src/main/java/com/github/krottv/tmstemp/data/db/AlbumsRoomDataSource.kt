package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.data.room.MyDatabase
import com.github.krottv.tmstemp.domain.AlbumModel

class AlbumsRoomDataSource(database: MyDatabase): AlbumDbDataSource {

    private val dao = database.provideDao()

    override suspend fun saveAlbums(list: List<AlbumModel>) {
        dao.saveAlbums(list)
    }

    override suspend fun getAlbums(): List<AlbumModel> {
        return dao.getAlbums().map { it.copy(name = it.name + "_room") }
    }
}
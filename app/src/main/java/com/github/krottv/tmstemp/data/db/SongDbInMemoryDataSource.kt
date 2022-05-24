package com.github.krottv.tmstemp.data.db

import com.github.krottv.tmstemp.domain.SongModel
import com.github.krottv.tmstemp.domain.SongsJSON

class SongDbInMemoryDataSource : SongDbDataSource {

    private var inMemoryData: MutableMap<Long, List<SongModel>>? = null

    override suspend fun saveSongs(list: SongsJSON) {
        if (inMemoryData == null) {
            inMemoryData = mutableMapOf(list.album.id to list.tracks.map {it.copy(title = it.title + " (cache)")})
        } else {
            inMemoryData!![list.album.id] = list.tracks.map {it.copy(title = it.title + " (cache)")}
        }
    }

    override suspend fun getSongs(): Map<Long, List<SongModel>>? {
        return inMemoryData
    }
}
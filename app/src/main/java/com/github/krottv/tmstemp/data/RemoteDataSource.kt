package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.MessageModel

interface RemoteDataSource {
    suspend fun getMessages(): List<MessageModel>
}
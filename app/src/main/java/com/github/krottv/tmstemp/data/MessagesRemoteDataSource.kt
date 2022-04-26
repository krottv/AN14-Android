package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.MessageModel

interface MessagesRemoteDataSource {
    suspend fun getMessages(): List<MessageModel>
}
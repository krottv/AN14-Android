package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.Message

interface MessagesRemoteDataSource {
    suspend fun getMessages(): List<Message>
}
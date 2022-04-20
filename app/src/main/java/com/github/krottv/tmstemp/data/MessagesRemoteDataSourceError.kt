package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.Message
import kotlinx.coroutines.delay
import java.lang.IllegalStateException

class MessagesRemoteDataSourceError: MessagesRemoteDataSource {
    override suspend fun getMessages(): List<Message> {
        delay(2000)
        throw IllegalStateException("IllegalStateException")
    }
}
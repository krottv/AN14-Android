package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.MessageModel
import kotlinx.coroutines.delay
import java.lang.IllegalStateException

class RemoteDataSourceError: RemoteDataSource {
    override suspend fun getMessages(): List<MessageModel> {
        delay(2000)
        throw IllegalStateException("IllegalStateException")
    }
}
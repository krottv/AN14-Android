package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.MessageModel
import kotlinx.coroutines.delay

class MessagesRemoteDataSourceFake: MessagesRemoteDataSource {

    companion object {
        const val MESSAGES_COUNT = 1000
    }
    override suspend fun getMessages(): List<MessageModel> {
        delay(2000)
        val message = MessageModel(
            "https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80",
            "Пункт такойто",
            "Какой-то текст Какой-то текст Какой-то текст Какой-то текст"
        )
        val result = ArrayList<MessageModel>(MESSAGES_COUNT)
        for (i in 0..MESSAGES_COUNT) {
            result.add(message.copy(mainText = "Пункт $i"))
        }
        return result
    }
}
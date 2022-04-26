package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.MessageModel
import kotlinx.coroutines.delay

class MessagesRemoteDataSourceFake: MessagesRemoteDataSource {
    override suspend fun getMessages(): List<MessageModel> {
        delay(2000)
        val message = MessageModel(
            "https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80",
            "Пункт такойто",
            "Какой-то текст Какой-то текст Какой-то текст Какой-то текст"
        )
        val result = ArrayList<MessageModel>(20)
        for (i in 0..20) {
            result.add(message.copy(mainText = "Пункт $i"))
        }
        return result
    }
}
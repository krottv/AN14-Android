package com.tms.android.ffthtask

import kotlinx.coroutines.delay

class MessageRemoteDataSourceFake: MessageRemoteDataSource {

    override suspend fun getMessages(): MutableList<Message> {
        val messages = mutableListOf<Message>()
        for(index in 1..1000){
            val msg = Message()
            msg.mainText = "Some txt $index"
            msg.subText = "Какой-то текст Какой-то текст Какой-то текст Какой-то текст"
            when {
                index % 3 == 0 -> {
                    msg.imgLink = "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
                }
                index % 5 == 0 -> {
                    msg.imgLink = "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
                }
                index % 7 == 0 -> {
                    msg.imgLink = "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
                }
                else -> {
                    msg.imgLink = "https://images.unsplash.com/photo-1505062351414-586330b076f2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1080&q=80"
                }
            }
            messages += msg
        }
        delay(2000L)
        return messages
    }
}
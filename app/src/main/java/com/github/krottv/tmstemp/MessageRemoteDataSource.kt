package com.tms.android.ffthtask

interface MessageRemoteDataSource {
    suspend fun getMessages(): List<Message>
}
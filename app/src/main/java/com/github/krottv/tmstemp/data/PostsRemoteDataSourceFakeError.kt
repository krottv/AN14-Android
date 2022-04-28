package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay

class PostsRemoteDataSourceFakeError : PostsRemoteDataSource {
    override suspend fun getPosts(): List<PostModel> {
        delay(2000L)
        throw Exception("Error")
    }
}
package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay

class PostRemoteDataSourceFakeError :PostRemoteDataSource{
    override suspend fun getPosts(): List<PostModel> {
        delay(4000L)
       throw IllegalStateException("Server error")
    }
}
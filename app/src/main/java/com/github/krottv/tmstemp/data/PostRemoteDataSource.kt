package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel

interface PostRemoteDataSource {
    suspend fun getPosts():List<PostModel>
}
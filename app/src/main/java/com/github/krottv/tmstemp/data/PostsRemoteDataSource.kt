package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel

interface PostsRemoteDataSource {
    suspend fun getPosts(): List<PostModel>
}
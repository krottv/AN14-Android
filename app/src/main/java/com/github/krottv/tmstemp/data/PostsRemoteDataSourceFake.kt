package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay

class PostsRemoteDataSourceFake : PostsRemoteDataSource {

    override suspend fun getPosts(): List<PostModel> {
        delay(2000L)

        val model = PostModel(
            "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
            "Пункт",
            "Какой-то текст Какой-то текст Какой-то текст Какой-то текст"
        )

        val mutableListOf = ArrayList<PostModel>(1000)

        for (i in 0..1000) {
            mutableListOf.add(model.copy(title = "${model.title} $i"))

        }
        return mutableListOf
    }
}
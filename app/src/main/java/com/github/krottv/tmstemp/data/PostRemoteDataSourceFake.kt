package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay


class PostRemoteDataSourceFake : PostRemoteDataSource {

    override suspend fun getPosts(): List<PostModel> {
        delay(1000L)
        val model = PostModel(
            "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixi" +
                    "d=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
            "Some title",
            "Aenean turpis nibh, mattis at erat vitae, consequat sagittis nisi.",
            "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixi" +
                    "d=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        )
        val mutableListOf = ArrayList<PostModel>(1000)
        for (i in 0..1000) {
            mutableListOf.add(model.copy())
        }
        return mutableListOf

    }
}
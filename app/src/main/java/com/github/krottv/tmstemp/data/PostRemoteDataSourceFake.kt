package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay

class PostRemoteDataSourceFake : PostRemoteDataSource {

    override suspend fun getPosts(): List<PostModel> {
        delay(200L)
        val model = PostModel(
            "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixi" +
                    "d=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
            "Title",
            "Cras ut dictum diam. Cras sed consectetur risus, quis tristique erat. Nam hendrerit mi sed dui semper. ",

            )
        val mutableListOf = ArrayList<PostModel>(1000)
        for (i in 0..1000) {
            mutableListOf.add(model.copy(title = "Some title $i"))
        }
        return mutableListOf

    }
}
package com.github.krottv.tmstemp.data

import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.delay

class PostRemoteDataSourceFake : PostRemoteDataSource {

    override suspend fun getPosts(): List<PostModel> {
        delay(4000L)
        val model = PostModel(
            "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib" +
                    "=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80",
            "User",
            "Cras ut dictum diam. Cras sed consectetur risus, quis tristique erat." +
                    "Nam hendrerit mi sed dui.",
            "  Close  "
        )
        val mutableListOf = ArrayList<PostModel>(1000)
        for (i in 0..1000) {
            mutableListOf.add(model.copy(title = "User $i"))
        }
        return mutableListOf

    }
}
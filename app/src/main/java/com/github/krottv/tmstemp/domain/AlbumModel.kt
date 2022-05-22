package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val id: Long,
    val image: String,
    val name: String,
    val trackCount: Int
) {

}
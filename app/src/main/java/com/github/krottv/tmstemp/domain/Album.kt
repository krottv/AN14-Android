package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val image: String,
    val name: String,
    val trackCount: Int
)
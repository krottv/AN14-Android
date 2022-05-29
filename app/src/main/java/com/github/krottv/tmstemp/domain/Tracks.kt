package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val artist: String,
    val image: String,
    val title: String,
    val url: String
)
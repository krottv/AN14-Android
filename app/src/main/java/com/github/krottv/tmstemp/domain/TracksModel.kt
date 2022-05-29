package com.github.krottv.tmstemp.domain

import kotlinx.serialization.Serializable

@Serializable
data class TracksModel(
    val album: Album,
    val tracks: List<Tracks>
)
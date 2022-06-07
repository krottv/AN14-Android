package com.github.krottv.tmstemp.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationPrefs(val turnToastOnLoading: Boolean)

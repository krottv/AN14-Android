package com.example.app.data.purchase

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}

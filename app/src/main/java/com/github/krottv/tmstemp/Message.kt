package com.tms.android.ffthtask

import java.util.*

data class Message(val id: UUID = UUID.randomUUID(), var mainText: String = "Some txt", var subText: String = "Some txt", var imgLink: String = "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"){}

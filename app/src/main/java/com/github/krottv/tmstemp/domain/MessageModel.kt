package com.github.krottv.tmstemp.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class MessageModel(val mainImage: String, val mainText: String, val littleText: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    fun transitionId() = mainText + "_"

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mainImage)
        parcel.writeString(mainText)
        parcel.writeString(littleText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageModel> {
        override fun createFromParcel(parcel: Parcel): MessageModel {
            return MessageModel(parcel)
        }

        override fun newArray(size: Int): Array<MessageModel?> {
            return arrayOfNulls(size)
        }
    }
}
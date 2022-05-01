package com.github.krottv.tmstemp.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class PostModel(val image: String, val title: String, val subtitle: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString()!!, parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) { parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(subtitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostModel> {
        override fun createFromParcel(parcel: Parcel): PostModel {
            return PostModel(parcel)
        }

        override fun newArray(size: Int): Array<PostModel?> {
            return arrayOfNulls(size)
        }
    }
}
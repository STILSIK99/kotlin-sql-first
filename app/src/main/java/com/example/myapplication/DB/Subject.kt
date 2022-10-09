package com.example.myapplication.DB

import android.os.Parcel
import android.os.Parcelable
import kotlin.math.min

class Subject(val title: String, val content: String, val id: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Subject> {
        override fun createFromParcel(parcel: Parcel): Subject {
            return Subject(parcel)
        }

        override fun newArray(size: Int): Array<Subject?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
//        return  "$id:${title.substring(0, min(10, title.length - 1))}:${content.substring(0,min(10, content.length - 1))}"
        return  "$id:$title:$content"
    }
}
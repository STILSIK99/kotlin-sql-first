package com.example.myapplication.DB

import android.os.Parcel
import android.os.Parcelable
import kotlin.math.min

class Subject(val id: Int, val title: String, val content: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
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
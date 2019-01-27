package com.sunmoonblog.gpuprofilerdemo

import android.os.Parcel
import android.os.Parcelable

data class ColorItem(val id: Int, val color: Int, val title: String?, val desc: String?) :  Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(color)
        parcel.writeString(title)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorItem> {
        override fun createFromParcel(parcel: Parcel): ColorItem {
            return ColorItem(parcel)
        }

        override fun newArray(size: Int): Array<ColorItem?> {
            return arrayOfNulls(size)
        }
    }
}

package com.example.tasks.DataClass

import android.media.Image
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDataClass(val title:String = "", val amount:String="", val image: String="") :
    Parcelable

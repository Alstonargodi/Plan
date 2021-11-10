package com.example.wetterbericht.model.Firebase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Insidedo(
    val title: String = "",
    val desc: String = "",
    val kat : String = "",
    val deadlinedate: String = "",
    val deadlinetime : String = "" ,
    val status: String = "",
): Parcelable


@Parcelize
data class Subtaskdo(
    val idsub : String = "",
    val task : String = ""
):Parcelable
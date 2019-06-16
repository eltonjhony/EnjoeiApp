package com.eltonjhony.enjoeiapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val name: String,
    val avatar: Photo
) : Parcelable {

    @IgnoredOnParcel val photo = avatar.getUrl(90)
}
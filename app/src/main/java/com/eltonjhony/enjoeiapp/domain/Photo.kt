package com.eltonjhony.enjoeiapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val publicId: String,
    val crop: String,
    val gravity: String) : Parcelable {

    fun getUrl(size: Int? = null): String = size?.let {
        "https://res.cloudinary.com/ddb86uj5i/image/upload/c_$crop,g_$gravity,w_$it,h_$it,r_max/$publicId.jpg"
    } ?: "https://res.cloudinary.com/ddb86uj5i/image/upload/c_$crop,g_$gravity/$publicId.jpg"
}
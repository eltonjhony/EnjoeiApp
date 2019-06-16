package com.eltonjhony.enjoeiapp.domain

import android.os.Parcelable
import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val discountPercentage: Int,
    val title: String,
    val price: Double,
    val originalPrice: Double,
    val size: String?,
    val likesCount: Int,
    val maximumInstallment: Int,
    val publishedCommentsCount: Int,
    val content: String,
    val user: User,
    val photos: List<Photo>) : Parcelable {

    @IgnoredOnParcel val defaultPhoto = photos.firstOrNull()?.getUrl()

    fun getFormattedSize(): String = size?.let { "- $it" } ?: ""
    fun getFormattedDiscountPercentage(): String = if (hasDiscount()) "$discountPercentage%" else ""
    fun getFormattedOriginalPrice() : String = if (hasOriginalPrice()) originalPrice.toBrlCurrency() else ""

    fun hasDiscount() = discountPercentage > 0
    private fun hasOriginalPrice() = originalPrice > price
}
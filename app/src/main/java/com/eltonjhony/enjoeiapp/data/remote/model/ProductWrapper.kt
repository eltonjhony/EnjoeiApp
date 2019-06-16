package com.eltonjhony.enjoeiapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductWrapper(val products: List<ProductResponse>)

data class ProductResponse(
    val id: Long,
    @SerializedName("discount_percentage")
    val discountPercentage: Int,
    val title: String,
    val price: Double,
    @SerializedName("original_price")
    val originalPrice: Double,
    val size: String?,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("maximum_installment")
    val maximumInstallment: Int,
    @SerializedName("published_comments_count")
    val publishedCommentsCount: Int,
    val content: String,
    val user: UserResponse,
    val photos: List<PhotoResponse>
)

data class UserResponse (
    val id: Long,
    val name: String,
    val avatar: PhotoResponse
)

data class PhotoResponse(
    @SerializedName( "public_id")
    val publicId: String,
    val crop: String,
    val gravity: String)
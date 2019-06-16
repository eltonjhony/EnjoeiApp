package com.eltonjhony.enjoeiapp.data.local.entities

import androidx.room.*

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey @ColumnInfo(name = "product_id") val id: Long,
    @ColumnInfo(name = "discount_percentage") val discountPercentage: Int,
    val title: String,
    val price: Double,
    @ColumnInfo(name = "original_price") val originalPrice: Double,
    val size: String?,
    @ColumnInfo(name = "likes_count") val likesCount: Int,
    @ColumnInfo(name = "maximum_installment") val maximumInstallment: Int,
    @ColumnInfo(name = "published_comments_count") val publishedCommentsCount: Int,
    val content: String,
    @Embedded(prefix = "user_") val user: UserEntity,
    @Embedded(prefix = "photo_") val photo: PhotoEntity)
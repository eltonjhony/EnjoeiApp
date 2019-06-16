package com.eltonjhony.enjoeiapp.data.local.entities.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.local.entities.ProductEntity
import com.eltonjhony.enjoeiapp.data.remote.model.ProductResponse
import com.eltonjhony.enjoeiapp.domain.Product

object ProductEntityDataMapper : DataMapper<ProductResponse, ProductEntity>() {

    override fun transform(entity: ProductResponse): ProductEntity {
        return ProductEntity(entity.id,
            entity.discountPercentage,
            entity.title,
            entity.price,
            entity.originalPrice,
            entity.size,
            entity.likesCount,
            entity.maximumInstallment,
            entity.publishedCommentsCount,
            entity.content,
            UserEntityDataMapper.transform(entity.user),
            PhotoEntityDataMapper.transform(entity.photos.first()))
    }

    override fun transform(entities: List<ProductResponse>): List<ProductEntity> {
        return entities.map { transform(it) }
    }

}

object ProductDataMapper : DataMapper<ProductEntity, Product>() {

    override fun transform(entity: ProductEntity): Product {
        return Product(entity.id,
            entity.discountPercentage,
            entity.title,
            entity.price,
            entity.originalPrice,
            entity.size,
            entity.likesCount,
            entity.maximumInstallment,
            entity.publishedCommentsCount,
            entity.content,
            UserDataMapper.transform(entity.user),
            listOf(PhotoDataMapper.transform(entity.photo)))
    }

    override fun transform(entities: List<ProductEntity>): List<Product> {
        return entities.map { transform(it) }
    }

}
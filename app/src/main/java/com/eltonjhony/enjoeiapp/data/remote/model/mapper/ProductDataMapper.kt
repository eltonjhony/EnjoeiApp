package com.eltonjhony.enjoeiapp.data.remote.model.mapper

import com.eltonjhony.enjoeiapp.data.DataMapper
import com.eltonjhony.enjoeiapp.data.remote.model.ProductResponse
import com.eltonjhony.enjoeiapp.domain.Product

object ProductDataMapper : DataMapper<ProductResponse, Product>() {

    override fun transform(entity: ProductResponse) : Product {
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
            PhotoDataMapper.transform(entity.photos))
    }

    override fun transform(entities: List<ProductResponse>): List<Product> {
        return entities.map { transform(it) }
    }
}
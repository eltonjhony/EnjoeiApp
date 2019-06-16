package com.eltonjhony.enjoeiapp.data.repository.datasource

import com.eltonjhony.enjoeiapp.data.local.cache.ProductCaching
import com.eltonjhony.enjoeiapp.data.local.entities.ProductEntity
import io.reactivex.Single

class LocalProductsDataSource(private val productCaching: ProductCaching) {

    fun getProducts(): Single<List<ProductEntity>> {
        return productCaching.getAll()
    }

    fun getPromoProducts(): Single<List<ProductEntity>> {
        return productCaching.getPromoProducts()
    }

}
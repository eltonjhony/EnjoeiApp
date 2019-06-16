package com.eltonjhony.enjoeiapp.data.local.cache

import com.eltonjhony.enjoeiapp.data.local.ProductDao
import com.eltonjhony.enjoeiapp.data.local.entities.ProductEntity
import com.eltonjhony.enjoeiapp.internal.infrastructure.PageCounterManager
import io.reactivex.Single

class ProductCaching(
    private val productDao: ProductDao,
    private val pageCounterManager: PageCounterManager) : Cache<ProductEntity>(pageCounterManager) {

    override fun getAll(): Single<List<ProductEntity>> {
        return Single.create { emitter ->
            emitter.onSuccess(productDao.getAllProducts())
        }
    }

    override fun putAll(page: Int, entities: List<ProductEntity>) {
        cache(page)
        entities.forEach { put(it) }
    }

    override fun put(entity: ProductEntity) {
        productDao.insert(entity)
    }

    fun getPromoProducts(): Single<List<ProductEntity>> {
        return Single.create { emitter ->
            emitter.onSuccess(productDao.getPromoProducts())
        }
    }
}
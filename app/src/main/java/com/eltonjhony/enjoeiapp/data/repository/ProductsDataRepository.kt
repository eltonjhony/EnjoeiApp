package com.eltonjhony.enjoeiapp.data.repository

import com.eltonjhony.enjoeiapp.data.local.cache.ProductCaching
import com.eltonjhony.enjoeiapp.data.repository.datasource.CloudProductsDataSource
import com.eltonjhony.enjoeiapp.data.repository.datasource.LocalProductsDataSource
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.repository.ProductsRepository
import io.reactivex.Single
import com.eltonjhony.enjoeiapp.data.local.entities.mapper.ProductDataMapper as localProductMapper
import com.eltonjhony.enjoeiapp.data.remote.model.mapper.ProductDataMapper as cloudProductMapper

class ProductsDataRepository(private val cloudProductsDataSource: CloudProductsDataSource,
                             private val localProductsDataSource: LocalProductsDataSource,
                             private val productCaching: ProductCaching) : ProductsRepository {

    override fun getProductsBy(page: Int): Single<List<Product>> {
        return when {
            productCaching.hasCacheFor(page) -> localProductsDataSource.getProducts().map { localProductMapper.transform(it) }
            else -> cloudProductsDataSource.getProductsBy(page).map { cloudProductMapper.transform(it.products) }
        }
    }

    override fun getPromoProducts(): Single<List<Product>> {
        return localProductsDataSource.getPromoProducts().map { localProductMapper.transform(it) }
    }
}
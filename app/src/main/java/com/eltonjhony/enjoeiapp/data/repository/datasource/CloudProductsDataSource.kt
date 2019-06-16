package com.eltonjhony.enjoeiapp.data.repository.datasource

import com.eltonjhony.enjoeiapp.data.exception.NoNetworkException
import com.eltonjhony.enjoeiapp.data.local.cache.ProductCaching
import com.eltonjhony.enjoeiapp.data.local.entities.mapper.ProductEntityDataMapper
import com.eltonjhony.enjoeiapp.data.remote.model.ProductWrapper
import com.eltonjhony.enjoeiapp.data.remote.NetworkHandler
import com.eltonjhony.enjoeiapp.data.remote.ProductService
import io.reactivex.Single

class CloudProductsDataSource(private val productService: ProductService,
                              private val productCaching: ProductCaching,
                              private val networkHandler: NetworkHandler) {

    fun getProductsBy(page: Int): Single<ProductWrapper> {
        return when {
            networkHandler.isConnected -> productService.getProductsBy(page).map {
                productCaching.putAll(page, ProductEntityDataMapper.transform(it.products))
                it
            }
            else -> Single.create { emitter -> emitter.onError(NoNetworkException()) }
        }
    }

}
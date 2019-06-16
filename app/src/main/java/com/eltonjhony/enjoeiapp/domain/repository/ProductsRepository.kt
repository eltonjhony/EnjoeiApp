package com.eltonjhony.enjoeiapp.domain.repository

import com.eltonjhony.enjoeiapp.domain.Product
import io.reactivex.Single

interface ProductsRepository {
    fun getProductsBy(page: Int): Single<List<Product>>
    fun getPromoProducts(): Single<List<Product>>
}
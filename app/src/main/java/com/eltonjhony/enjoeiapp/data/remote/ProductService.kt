package com.eltonjhony.enjoeiapp.data.remote

import com.eltonjhony.enjoeiapp.data.remote.model.ProductWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products/home")
    fun getProductsBy(@Query("page") page: Int): Single<ProductWrapper>
}
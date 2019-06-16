package com.eltonjhony.enjoeiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eltonjhony.enjoeiapp.data.local.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductEntity)

    @Query("SELECT * FROM product")
    fun getAllProducts() : List<ProductEntity>

    @Query("SELECT * FROM product WHERE original_price > price")
    fun getPromoProducts() : List<ProductEntity>
}
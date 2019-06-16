package com.eltonjhony.enjoeiapp.domain

import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductTest {

    @Test
    fun `product should have original price formatted`() {
        val product = Product(
            1L,
            10, "fake product title", 100.00,
            200.0, "", 10, 2, 11,
            "fake product description", user, listOf(photo)
        )

        val originalPrice = product.getFormattedOriginalPrice()
        assert(originalPrice.isNotBlank())
        assert(originalPrice == product.originalPrice.toBrlCurrency())
    }

    @Test
    fun `product should have discount percentage formatted`() {
        val product = Product(
            1L,
            10, "fake product title", 100.00,
            200.0, "", 10, 2, 11,
            "fake product description", user, listOf(photo)
        )

        val discountPercentage = product.getFormattedDiscountPercentage()
        assert(discountPercentage.isNotBlank())
        assert(discountPercentage == "${product.discountPercentage}%")
    }

    companion object {
        private val photo = Photo("fake public id", "fake crop", "fake gravity")
        private val user = User(1L, "fake username", photo)
    }

}
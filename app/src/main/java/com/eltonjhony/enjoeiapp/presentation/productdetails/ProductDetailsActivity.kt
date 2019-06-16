package com.eltonjhony.enjoeiapp.presentation.productdetails

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.displayHomeAsUpEnabled
import com.eltonjhony.enjoeiapp.internal.extensions.load
import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.product_content.*

class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_EXTRA_KEY = "PRODUCT_EXTRA_PARAM"

        fun callingIntent(context: Context?, product: Product) {
            context?.startActivity(Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra(PRODUCT_EXTRA_KEY, product)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        displayHomeAsUpEnabled(toolbar)

        populateUi()
        motionLayoutContainer.transitionToEnd()
    }

    private fun populateUi() {
        val product = intent.getParcelableExtra<Product>(PRODUCT_EXTRA_KEY)

        productImageView.load(product.defaultPhoto)
        priceTextView.text = product.price.toBrlCurrency()
        titleTextView.text = product.title
        contentTextView.text = product.content
        socialCounterView.commentsCount = product.publishedCommentsCount

        originalPriceTextView.apply {
            text = product.getFormattedOriginalPrice()
            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        descTextView.text = when {
            product.hasDiscount() -> getString(
                R.string.price_with_discount_description,
                product.getFormattedDiscountPercentage(),
                product.maximumInstallment
            )
            else -> getString(R.string.price_without_discount_description, product.maximumInstallment)
        }
    }

}
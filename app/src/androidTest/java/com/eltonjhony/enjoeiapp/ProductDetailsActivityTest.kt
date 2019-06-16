package com.eltonjhony.enjoeiapp

import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eltonjhony.enjoeiapp.domain.Photo
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.User
import com.eltonjhony.enjoeiapp.internal.extensions.isEven
import com.eltonjhony.enjoeiapp.internal.extensions.toBrlCurrency
import com.eltonjhony.enjoeiapp.presentation.productdetails.ProductDetailsActivity
import com.eltonjhony.enjoeiapp.utils.ViewActions.collapseAppBarLayout
import com.eltonjhony.enjoeiapp.utils.ViewActions.waitFor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailsActivityTest {

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Rule
    @JvmField
    val rule: ActivityTestRule<ProductDetailsActivity> =
        object : ActivityTestRule<ProductDetailsActivity>(ProductDetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                return Intent(context, ProductDetailsActivity::class.java).apply {
                    putExtra(ProductDetailsActivity.PRODUCT_EXTRA_KEY, product)
                }
            }
        }

    @Before fun setup() {
        onView(isRoot()).perform(waitFor())
        onView(withId(R.id.appBarLayout)).perform(collapseAppBarLayout())
    }

    @Test
    fun checkImmutableProductItemsVisibility() {
        onView(withId(R.id.priceTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.socialCounterView)).check(matches(isDisplayed()))
        onView(withId(R.id.descTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.contentTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkProductViewPopulated() {
        val price = product.price.toBrlCurrency()
        onView(withId(R.id.priceTextView)).check(matches(withText(price)))
        onView(withId(R.id.originalPriceTextView)).check(matches(withText(product.getFormattedOriginalPrice())))
        onView(withId(R.id.titleTextView)).check(matches(withText(product.title)))
        onView(withId(R.id.contentTextView)).check(matches(withText(product.content)))

        val discountLabel = when {
            product.hasDiscount() -> context.getString(R.string.price_with_discount_description, product.getFormattedDiscountPercentage(), product.maximumInstallment)
            else -> context.getString(R.string.price_without_discount_description, product.maximumInstallment)
        }

        onView(withId(R.id.descTextView)).check(matches(withText(discountLabel)))
    }

    companion object {
        private fun headsOrTails() = (1..2).shuffled().first()
        private fun randomPrice() = if (headsOrTails().isEven()) 100.0 else 200.0
        private fun randomDiscount() = if (headsOrTails().isEven()) 0 else 10

        private val photo = Photo("fake public id", "fake crop", "fake gravity")
        private val user = User(1L, "fake username", photo)

        val product = Product(1L, randomDiscount(), "fake product title", randomPrice(),
            randomPrice(), "", 10, 2, 11,
            "fake product description", user, listOf(photo)
        )
    }
}
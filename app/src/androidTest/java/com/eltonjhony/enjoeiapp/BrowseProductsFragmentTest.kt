package com.eltonjhony.enjoeiapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eltonjhony.enjoeiapp.presentation.home.HomeActivity
import com.eltonjhony.enjoeiapp.presentation.views.BrowseView
import com.eltonjhony.enjoeiapp.utils.RecyclerViewMatcher.withRecyclerView
import com.eltonjhony.enjoeiapp.utils.ViewAssertions.doesNotExistOrGone
import com.eltonjhony.enjoeiapp.utils.ViewAssertions.withFontSize
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseProductsFragmentTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(HomeActivity::class.java)

    @Test
    fun testProductCardClick() {
        IdlingRegistry.getInstance().register(BrowseView.countingIdlingResource)

        onView(withRecyclerView(R.id.productsRecyclerView)
            .atPositionOnView(1, R.id.productItemView))
            .perform(click())

        intended(allOf(
            hasComponent(hasShortClassName(".presentation.productdetails.ProductDetailsActivity")),
            toPackage("com.eltonjhony.enjoeiapp")
        ))
    }

    @Test
    fun checkProductTitleView() {
        IdlingRegistry.getInstance().register(BrowseView.countingIdlingResource)

        onView(withRecyclerView(R.id.productsRecyclerView)
            .atPositionOnView(1, R.id.titleTextView))
            .check(matches(withText("Tenis maneiro")))

        onView(withRecyclerView(R.id.productsRecyclerView)
            .atPositionOnView(1, R.id.titleTextView))
            .check(matches(withFontSize(16f)))
    }

    @Test
    fun checkDiscountViewTag() {
        IdlingRegistry.getInstance().register(BrowseView.countingIdlingResource)

        onView(withRecyclerView(R.id.productsRecyclerView)
            .atPositionOnView(1, R.id.discountView))
            .check(doesNotExistOrGone())

        onView(withRecyclerView(R.id.productsRecyclerView)
            .atPositionOnView(2, R.id.discountView))
            .check(matches(isDisplayed()))
    }
}
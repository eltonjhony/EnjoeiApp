package com.eltonjhony.enjoeiapp.utils

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.util.HumanReadables
import android.view.View
import android.widget.TextView
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher

object ViewAssertions {
    fun doesNotExistOrGone(): ViewAssertion {
        return ViewAssertion { view, _ ->
            view?.let {
                when (it.visibility) {
                    View.VISIBLE -> {
                        assertThat(
                            "View is present in the hierarchy and not GONE: " + HumanReadables.describe(view),
                            true,
                            `is`(false)
                        )
                    }
                }
            }
        }
    }

    fun withFontSize(expectedSize: Float): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {

            override fun matchesSafely(target: View): Boolean {
                if (target !is TextView) {
                    return false
                }
                val pixels = target.textSize
                val actualSize = pixels / target.getResources().displayMetrics.scaledDensity
                return java.lang.Float.compare(actualSize, expectedSize) == 0
            }

            override fun describeTo(description: Description) {
                description.appendText("with fontSize: ")
                description.appendValue(expectedSize)
            }
        }
    }
}
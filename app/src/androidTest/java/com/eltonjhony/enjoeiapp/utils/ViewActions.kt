package com.eltonjhony.enjoeiapp.utils

import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import android.view.View
import org.hamcrest.Matcher
import com.google.android.material.appbar.AppBarLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom

object ViewActions {

    fun waitFor(millis: Long = 1000): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }

    fun collapseAppBarLayout(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(AppBarLayout::class.java)
            }

            override fun getDescription(): String {
                return "Collapse App Bar Layout"
            }

            override fun perform(uiController: UiController, view: View) {
                val appBarLayout = view as AppBarLayout
                appBarLayout.setExpanded(false)
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}
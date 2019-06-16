package com.eltonjhony.enjoeiapp.internal.infrastructure

import android.content.SharedPreferences

class PageCounterManager(private val manager: SharedPreferences) {

    fun getCurrentPage() = manager.getInt("page", 0)
    fun put(page: Int) = manager.edit().putInt("page", page).apply()
    fun invalidate() = manager.edit().clear().apply()

}
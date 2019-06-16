package com.eltonjhony.enjoeiapp.data.local.cache

import com.eltonjhony.enjoeiapp.internal.infrastructure.PageCounterManager
import io.reactivex.Single

abstract class Cache<T>(private val pageCounterManager: PageCounterManager) {

    fun hasCacheFor(page: Int) = pageCounterManager.getCurrentPage() >= page
    fun cache(page: Int) = pageCounterManager.put(page)
    fun invalidate() = pageCounterManager.invalidate()

    abstract fun getAll() : Single<List<T>>
    abstract fun putAll(page: Int, entities: List<T>)
    abstract fun put(entity: T)
}
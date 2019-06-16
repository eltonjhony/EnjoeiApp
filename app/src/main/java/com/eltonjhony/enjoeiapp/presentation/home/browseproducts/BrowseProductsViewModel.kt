package com.eltonjhony.enjoeiapp.presentation.home.browseproducts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.interactor.GetProductsUseCase
import com.eltonjhony.enjoeiapp.domain.interactor.GetProductsUseCase.Params
import com.eltonjhony.enjoeiapp.internal.extensions.setFailure
import com.eltonjhony.enjoeiapp.internal.extensions.setLoading
import com.eltonjhony.enjoeiapp.internal.extensions.setSuccess
import com.eltonjhony.enjoeiapp.internal.infrastructure.PageCounterManager
import com.eltonjhony.enjoeiapp.presentation.common.Resource

class BrowseProductsViewModel(private val getProductsUseCase: GetProductsUseCase,
                              private val pageCounterManager: PageCounterManager) : ViewModel() {

    val products = MutableLiveData<Resource<List<Product>>>()

    fun load(page: Int) {
        products.setLoading()
        getProductsUseCase(Params(page)) { result: List<Product>?, throwable: Throwable? ->
            handleMovieList(result)
            handleFailure(throwable)
        }
    }

    fun getCurrentPage(): Int {
        val currentPage = pageCounterManager.getCurrentPage()
        return when {
            currentPage > 0 -> currentPage
            else -> currentPage + 1
        }
    }

    fun resetPageCounter() = pageCounterManager.invalidate()

    private fun handleMovieList(data: List<Product>?) = data?.let { products.setSuccess(data) }
    private fun handleFailure(throwable: Throwable?) = throwable?.let { products.setFailure(it) }

    override fun onCleared() {
        getProductsUseCase.dispose()
        super.onCleared()
    }
}
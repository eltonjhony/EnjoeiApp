package com.eltonjhony.enjoeiapp.presentation.promotions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.interactor.GetPromoProductsUseCase
import com.eltonjhony.enjoeiapp.internal.extensions.setFailure
import com.eltonjhony.enjoeiapp.internal.extensions.setSuccess
import com.eltonjhony.enjoeiapp.presentation.common.Resource
import com.eltonjhony.enjoeiapp.domain.interactor.GetPromoProductsUseCase.Params

class PromotionsViewModel(private val getPromotionsUseCase: GetPromoProductsUseCase) : ViewModel() {

    val products = MutableLiveData<Resource<List<Product>>>()

    fun load() {
        getPromotionsUseCase(params = Params()) { result: List<Product>?, throwable: Throwable? ->
            handlePromoList(result)
            handleFailure(throwable)
        }
    }

    private fun handlePromoList(data: List<Product>?) = data?.let { products.setSuccess(data) }
    private fun handleFailure(throwable: Throwable?) = throwable?.let { products.setFailure(it) }

    override fun onCleared() {
        getPromotionsUseCase.dispose()
        super.onCleared()
    }
}
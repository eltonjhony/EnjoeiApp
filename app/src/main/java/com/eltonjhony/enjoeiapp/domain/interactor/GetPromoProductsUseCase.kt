package com.eltonjhony.enjoeiapp.domain.interactor

import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.executor.SchedulersFacade
import com.eltonjhony.enjoeiapp.domain.repository.ProductsRepository
import io.reactivex.Single
import com.eltonjhony.enjoeiapp.domain.interactor.GetPromoProductsUseCase.Params

class GetPromoProductsUseCase(
    private val productsRepository: ProductsRepository, schedulersFacade: SchedulersFacade
) : UseCase<List<Product>, Params>(schedulersFacade) {

    override fun run(params: Params): Single<List<Product>> = productsRepository.getPromoProducts()

    class Params
}
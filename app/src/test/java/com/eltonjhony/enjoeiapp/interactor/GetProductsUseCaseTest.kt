package com.eltonjhony.enjoeiapp.interactor

import com.eltonjhony.enjoeiapp.data.exception.NoNetworkException
import com.eltonjhony.enjoeiapp.domain.Photo
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.domain.User
import com.eltonjhony.enjoeiapp.domain.executor.SchedulersFacade
import com.eltonjhony.enjoeiapp.domain.interactor.GetProductsUseCase
import com.eltonjhony.enjoeiapp.domain.interactor.GetProductsUseCase.Params
import com.eltonjhony.enjoeiapp.domain.repository.ProductsRepository
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

@RunWith(JUnit4::class)
class GetProductsUseCaseTest {

    @Mock lateinit var productsDataRepository: ProductsRepository
    @Mock lateinit var schedulerFacade: SchedulersFacade

    lateinit var getProductsUseCase: GetProductsUseCase

    @Before fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductsUseCase = GetProductsUseCase(productsDataRepository, schedulerFacade)
        whenever(schedulerFacade.io()).thenReturn(TestScheduler())
        whenever(schedulerFacade.ui()).thenReturn(TestScheduler())
    }

    @Test
    fun `get products with success`() {
        whenever(productsDataRepository.getProductsBy(PAGE)).thenReturn(Single.just(products))

        getProductsUseCase(Params(1)) { result: List<Product>?, _: Throwable? ->
            assert(result == products)
            verify(productsDataRepository).getProductsBy(PAGE)
        }
    }

    @Test
    fun `get products without network connection`() {
        whenever(productsDataRepository.getProductsBy(PAGE)).thenReturn(Single.error(NoNetworkException()))

        getProductsUseCase(Params(1)) { result: List<Product>?, throwable: Throwable? ->
            assert(result == null)
            assert(throwable?.message == "No Available Network found")
            verify(productsDataRepository).getProductsBy(PAGE)
        }
    }

    companion object {
        const val PAGE = 1

        private val photo = Photo("fake publicId", "fake crop", "fake gravity")
        private val user = User(1L, "fake user", photo)
        private val product = Product(1L, 10, "fake product title", 10.0,
            20.0, "", 5, 1, 0,
            "fake product content", user, listOf(photo))

        val products = listOf(product)
    }
}
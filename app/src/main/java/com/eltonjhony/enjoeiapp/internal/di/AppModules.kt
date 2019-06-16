package com.eltonjhony.enjoeiapp.internal.di

import android.content.Context
import com.eltonjhony.enjoeiapp.data.local.AppDatabase
import com.eltonjhony.enjoeiapp.data.local.cache.ProductCaching
import com.eltonjhony.enjoeiapp.data.remote.NetworkHandler
import com.eltonjhony.enjoeiapp.data.remote.ProductService
import com.eltonjhony.enjoeiapp.data.repository.ProductsDataRepository
import com.eltonjhony.enjoeiapp.data.repository.datasource.CloudProductsDataSource
import com.eltonjhony.enjoeiapp.data.repository.datasource.LocalProductsDataSource
import com.eltonjhony.enjoeiapp.domain.executor.SchedulersFacade
import com.eltonjhony.enjoeiapp.domain.interactor.GetProductsUseCase
import com.eltonjhony.enjoeiapp.domain.interactor.GetPromoProductsUseCase
import com.eltonjhony.enjoeiapp.domain.repository.ProductsRepository
import com.eltonjhony.enjoeiapp.internal.infrastructure.PageCounterManager
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.BrowseProductsViewModel
import com.eltonjhony.enjoeiapp.presentation.promotions.PromotionsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val presentationModule = module {
    viewModel { BrowseProductsViewModel(get(), get()) }
    viewModel { PromotionsViewModel(get()) }
}

val domainModule = module {
    single { GetProductsUseCase(get(), SchedulersFacade()) }
    single { GetPromoProductsUseCase(get(), SchedulersFacade()) }
}

val internalModule = module {
    single {
        PageCounterManager(
            manager = androidContext().getSharedPreferences(
                "com.eltonjhony.enjoeiapp.page.counter.manager",
                Context.MODE_PRIVATE
            )
        )
    }
}

val dataModule = module {
    single { ProductCaching(AppDatabase.getDatabase(androidContext()).productDao(), get()) }
    single<ProductsRepository> { ProductsDataRepository(get(), get(), get()) }
    single { CloudProductsDataSource(get(), get(), get()) }
    single { LocalProductsDataSource(get()) }
}

val cloudModule = module {
    single { NetworkHandler(androidContext()) }
    single<ProductService> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-9cb308-joeien.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ProductService::class.java)
    }
}

val appModules = listOf(internalModule, presentationModule, domainModule, dataModule, cloudModule)
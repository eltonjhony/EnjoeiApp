package com.eltonjhony.enjoeiapp.presentation.home.browseproducts

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.data.exception.NoNetworkException
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.presentation.common.Resource
import com.eltonjhony.enjoeiapp.presentation.common.ResourceState
import com.eltonjhony.enjoeiapp.presentation.productdetails.ProductDetailsActivity.Companion.callingIntent
import com.eltonjhony.enjoeiapp.presentation.promotions.PromotionsActivity.Companion.callingIntent
import kotlinx.android.synthetic.main.fragment_products_browse.*
import org.koin.android.viewmodel.ext.android.viewModel

class BrowseProductsFragment : Fragment() {

    private val productsViewModel: BrowseProductsViewModel by viewModel()
    private val adapter = ProductsAdapter(clickListener = { callingIntent(context, it) }, promotionClickListener = { callingIntent(context) })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_products_browse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewModel.products.observe(this, Observer { updateState(it) })

        browseView.setup(adapter)

        browseView.onRefresh {
            productsViewModel.resetPageCounter()
            loadProducts()
        }

        browseView.onLoadMore { nextPage ->
            loadProducts(nextPage)
        }

        loadProducts()
    }

    private fun loadProducts(page: Int = productsViewModel.getCurrentPage()) {
        productsViewModel.load(page)
    }

    private fun updateState(resource: Resource<List<Product>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> startLoading()
                ResourceState.SUCCESS -> {
                    stopLoading()
                    it.data?.let { products -> renderProductList(products) }
                }
                ResourceState.ERROR -> {
                    stopLoading()
                    it.throwable?.let { t -> renderFailure(t) }
                }
            }
        }
    }

    private fun renderProductList(products: List<Product>) {
        browseView.show()
        adapter.updateProducts(products)
    }

    private fun renderFailure(throwable: Throwable) {
        browseView.hide()
        when (throwable) {
            is NoNetworkException -> browseView.showNetworkingError {
                loadProducts()
            }
            else -> browseView.showGenericError()
        }
    }

    private fun stopLoading() {
        browseView.hideLoader()
    }

    private fun startLoading() {
        browseView.showLoader()
    }
}
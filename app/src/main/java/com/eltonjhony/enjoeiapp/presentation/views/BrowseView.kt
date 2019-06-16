package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.internal.extensions.hide
import com.eltonjhony.enjoeiapp.internal.extensions.show
import com.eltonjhony.enjoeiapp.presentation.common.EndlessRecyclerViewScrollListener
import com.eltonjhony.enjoeiapp.presentation.common.ItemOffsetWithHeaderDecoration
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.ProductsAdapter
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.ProductsAdapter.Companion.BANNER_VIEW_TYPE
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.ProductsAdapter.Companion.PRODUCTS_COLUMNS_PER_LINE
import com.eltonjhony.enjoeiapp.presentation.home.browseproducts.ProductsAdapter.Companion.PRODUCTS_COLUMNS_SPAN_SIZE
import kotlinx.android.synthetic.main.browse_view.view.*

class BrowseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    fun setup(productsAdapter: ProductsAdapter) {
        val gridLayoutManager = GridLayoutManager(
            context,
            PRODUCTS_COLUMNS_PER_LINE
        )
        productsRecyclerView.apply {
            layoutManager = gridLayoutManager
            addItemDecoration(ItemOffsetWithHeaderDecoration(BANNER_VIEW_TYPE))
            adapter = productsAdapter
        }
        gridLayoutManager.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (productsAdapter.getItemViewType(position)) {
                        BANNER_VIEW_TYPE -> spanCount
                        else -> PRODUCTS_COLUMNS_SPAN_SIZE
                    }
                }
            }
        }
    }

    fun onRefresh(refreshListener: () -> Unit) {
        swipeRefreshLayout.setOnRefreshListener { refreshListener.invoke().also {
            swipeRefreshLayout.isRefreshing = true
        } }
    }

    fun hideLoader() {
        if (!countingIdlingResource.isIdleNow) countingIdlingResource.decrement()
        swipeRefreshLayout.isRefreshing = false
        progressBar.hide()
    }

    fun showLoader() {
        countingIdlingResource.increment()
        if (swipeRefreshLayout.isRefreshing) progressBar.hide() else progressBar.show()
    }

    fun onLoadMore(loadListener: (nextPage: Int) -> Unit) {
        val layoutManager = productsRecyclerView.layoutManager as GridLayoutManager
        productsRecyclerView.apply {
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(nextPage: Int, totalItemsCount: Int, view: RecyclerView?) {
                    loadListener(nextPage)
                }
            })
        }
    }

    fun showNetworkingError(tryAgainListener: () -> Unit) {
        val errorTitle = resources.getString(R.string.connection_error_title)
        val errorDescription = resources.getString(R.string.connection_error_description)
        errorView.showError(errorTitle, errorDescription, tryAgainListener)
    }

    fun showGenericError() {
        val errorTitle = resources.getString(R.string.connection_error_title)
        val errorDescription = resources.getString(R.string.error_generic_description)
        errorView.showError(errorTitle, errorDescription)
    }

    fun show() {
        errorView.hide()
        swipeRefreshLayout.show()
    }

    fun hide() {
        errorView.show()
        swipeRefreshLayout.hide()
    }

    init {
        View.inflate(context, R.layout.browse_view, this)
    }

    companion object {
        val countingIdlingResource = CountingIdlingResource("countingIdlingResource")
    }
}
package com.eltonjhony.enjoeiapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.eltonjhony.enjoeiapp.R
import com.eltonjhony.enjoeiapp.domain.Product
import com.eltonjhony.enjoeiapp.internal.extensions.hide
import com.eltonjhony.enjoeiapp.presentation.productdetails.ProductDetailsActivity.Companion.callingIntent
import com.eltonjhony.enjoeiapp.presentation.promotions.PromoAdapter
import kotlinx.android.synthetic.main.browse_view.view.errorView
import kotlinx.android.synthetic.main.promo_view.view.*

class PromoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val promoAdapter = PromoAdapter { callingIntent(context, it) }

    fun setup() {
        val linearLayoutManager = LinearLayoutManager(context)
        promotionsRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = promoAdapter
        }
    }

    fun update(products: List<Product>) {
        errorView.hide()
        promoAdapter.updateProducts(products)
    }

    fun showGenericError() {
        promotionsRecyclerView.hide()
        val errorTitle = resources.getString(R.string.connection_error_title)
        val errorDescription = resources.getString(R.string.error_generic_description)
        errorView.showError(errorTitle, errorDescription)
    }

    init {
        View.inflate(context, R.layout.promo_view, this)
    }

}